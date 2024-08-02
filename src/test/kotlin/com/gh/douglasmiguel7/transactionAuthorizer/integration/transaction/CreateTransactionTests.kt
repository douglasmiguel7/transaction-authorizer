package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.APPROVED
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.NOT_ENOUGH_FUNDS
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.UNKNOWN
import java.util.UUID
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class CreateTransactionTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TransactionTestComponent,
) {

  @ParameterizedTest
  @MethodSource("provideValidationTestCases")
  fun `should approve or reject transaction based on validation`(testCase: TestCase) {
    val entity = testComponent.accountEntity(
      food = testCase.account.food,
      meal = testCase.account.meal,
      cash = testCase.account.cash,
    )

    val requestBody = testComponent.transactionRequestBody(
      accountId = entity.id!!,
      totalAmount = testCase.transaction.totalAmount,
      mcc = testCase.transaction.mcc,
      merchant = testCase.transaction.merchant,
    )

    mockMvc.perform(post("/transactions").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.code").value(testCase.expectedCode.code))
      .andDo(print())

    testComponent.cleanDatabase()
  }

  @ParameterizedTest
  @MethodSource("provideUnknownResponseCodeTestCases")
  fun `should reject transaction with code 07`(testCase: TestCase) {
    val requestBody = testComponent.transactionRequestBody(
      accountId = testCase.account.id,
      totalAmount = testCase.transaction.totalAmount,
      mcc = testCase.transaction.mcc,
      merchant = testCase.transaction.merchant,
    )

    mockMvc.perform(post("/transactions").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.code").value(testCase.expectedCode.code))
      .andDo(print())
  }

  data class TransactionInfo(
    val totalAmount: String?,
    val mcc: String?,
    val merchant: String?,
  )

  data class AccountInfo(
    val id: UUID? = null,
    val food: String,
    val meal: String,
    val cash: String,
  )

  data class TestCase(
    val transaction: TransactionInfo,
    val account: AccountInfo,
    val expectedCode: TransactionCode,
  )

  companion object {
    private fun unknownMcc(funds: String, totalAmount: String, expectedCode: TransactionCode): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = "unknown",
            merchant = "unknown",
          ),
          account = AccountInfo(
            food = "0",
            meal = "0",
            cash = funds,
          ),
          expectedCode = expectedCode,
        )
      )
    }

    private fun food(mcc: String, funds: String, cash: String, totalAmount: String, expectedCode: TransactionCode): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = mcc,
            merchant = "unknown",
          ),
          account = AccountInfo(
            food = funds,
            meal = "0",
            cash = cash,
          ),
          expectedCode = expectedCode,
        )
      )
    }

    private fun meal(mcc: String, funds: String, cash: String, totalAmount: String, expectedCode: TransactionCode): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = mcc,
            merchant = "unknown",
          ),
          account = AccountInfo(
            food = "0",
            meal = funds,
            cash = cash,
          ),
          expectedCode = expectedCode,
        )
      )
    }

    private fun mart(funds: String, cash: String, totalAmount: String, expectedCode: TransactionCode): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = "unknown",
            merchant = "mercado",
          ),
          account = AccountInfo(
            food = funds,
            meal = "0",
            cash = cash,
          ),
          expectedCode = expectedCode,
        )
      )
    }

    private fun restaurant(funds: String, cash: String, totalAmount: String, expectedCode: TransactionCode): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = "unknown",
            merchant = "restaurante",
          ),
          account = AccountInfo(
            food = "0",
            meal = funds,
            cash = cash,
          ),
          expectedCode = expectedCode,
        )
      )
    }

    private fun unknownCodeCase(totalAmount: String?, mcc: String?, merchant: String?, accountId: UUID?): Arguments {
      return Arguments.of(
        TestCase(
          transaction = TransactionInfo(
            totalAmount = totalAmount,
            mcc = mcc,
            merchant = merchant,
          ),
          account = AccountInfo(
            id = accountId,
            food = "0",
            meal = "0",
            cash = "0",
          ),
          expectedCode = UNKNOWN,
        )
      )
    }

    @JvmStatic
    fun provideValidationTestCases(): List<Arguments> {
      val approved = APPROVED
      val notEnoughFunds = NOT_ENOUGH_FUNDS

      return listOf(
        // approved cases
        unknownMcc(funds = "50", totalAmount = "10", expectedCode = approved),

        food(mcc = "5411", funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),
        food(mcc = "5412", funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),

        meal(mcc = "5811", funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),
        meal(mcc = "5812", funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),

        food(mcc = "5411", funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),
        food(mcc = "5412", funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),

        meal(mcc = "5811", funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),
        meal(mcc = "5812", funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),

        mart(funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),
        mart(funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),

        restaurant(funds = "50", cash = "0", totalAmount = "10", expectedCode = approved),
        restaurant(funds = "25", cash = "25", totalAmount = "50", expectedCode = approved),

        // not enough funds cases
        unknownMcc(funds = "10", totalAmount = "50", expectedCode = notEnoughFunds),

        food(mcc = "5411", funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),
        food(mcc = "5412", funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),

        meal(mcc = "5811", funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),
        meal(mcc = "5812", funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),

        mart(funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),

        restaurant(funds = "10", cash = "0", totalAmount = "50", expectedCode = notEnoughFunds),
      )
    }

    @JvmStatic
    fun provideUnknownResponseCodeTestCases(): List<Arguments>  {
      return listOf(
        unknownCodeCase(totalAmount = null, mcc = null, merchant = null, accountId = null),

        unknownCodeCase(totalAmount = null, mcc = null, merchant = null, accountId = UUID.randomUUID()),

        unknownCodeCase(totalAmount = null, mcc = null, merchant = "unknown", accountId = UUID.randomUUID()),

        unknownCodeCase(totalAmount = null, mcc = "unknown", merchant = "unknown", accountId = UUID.randomUUID()),

        unknownCodeCase(totalAmount = "0", mcc = "unknown", merchant = "unknown", accountId = UUID.randomUUID()),

        unknownCodeCase(totalAmount = "10", mcc = "unknown", merchant = "unknown", accountId = UUID.randomUUID()),
      )
    }
  }
}