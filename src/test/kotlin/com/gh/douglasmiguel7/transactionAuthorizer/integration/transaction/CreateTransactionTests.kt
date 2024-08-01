package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class CreateTransactionTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TransactionTestComponent,
) {

  fun createRequestBody(testCase: TestCase): String {
    return testComponent.transactionRequestBody(
      totalAmount = testCase.transaction.totalAmount,
      mcc = testCase.transaction.mcc,
      merchant = testCase.transaction.merchant,
      food = testCase.account.food,
      meal = testCase.account.meal,
      cash = testCase.account.cash,
    )
  }

  @ParameterizedTest
  @MethodSource("provideMccTestCase")
  fun `should approve or reject transaction based on mcc`(testCase: TestCase) {
    val requestBody = createRequestBody(testCase)

    mockMvc.perform(post("/transactions").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.code").value(testCase.expectedCode))

    testComponent.cleanDatabase()
  }

  data class TransactionInfo(
    val totalAmount: String,
    val mcc: String,
    val merchant: String,
  )

  data class AccountInfo(
    val food: String,
    val meal: String,
    val cash: String,
  )

  data class TestCase(
    val transaction: TransactionInfo,
    val account: AccountInfo,
    val expectedCode: String,
  )

  companion object {
    private fun unknownMcc(funds: String, totalAmount: String, expectedCode: String): Arguments {
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

    private fun food(mcc: String, funds: String, cash: String, totalAmount: String, expectedCode: String): Arguments {
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

    private fun meal(mcc: String, funds: String, cash: String, totalAmount: String, expectedCode: String): Arguments {
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

    private fun mart(funds: String, cash: String, totalAmount: String, expectedCode: String): Arguments {
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

    private fun restaurant(funds: String, cash: String, totalAmount: String, expectedCode: String): Arguments {
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

    @JvmStatic
    fun provideMccTestCase(): List<Arguments> {
      val approved = "00"
      val notEnoughFunds = "51"

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
  }
}