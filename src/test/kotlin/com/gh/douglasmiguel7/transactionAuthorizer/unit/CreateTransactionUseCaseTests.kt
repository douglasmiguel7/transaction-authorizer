package com.gh.douglasmiguel7.transactionAuthorizer.unit

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Merchant
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction.CreateTransactionUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CreateTransactionUseCaseTests(
  @Autowired val useCase: CreateTransactionUseCase,
) {

  @ParameterizedTest
  @MethodSource("provideTransactionToCheckMcc")
  fun `should return mcc as expected`(transaction: Transaction, expectedMcc: Mcc) {
    val mcc = useCase.getMcc(transaction)

    assertEquals(expectedMcc, mcc)
  }

  companion object {
    private fun withMerchant(merchant: String?): Transaction {
      return Transaction(
        id = null,
        accountId = null,
        totalAmount = null,
        mcc = null,
        merchant = merchant,
        code = null,
      )
    }

    private fun withMcc(mcc: String?): Transaction {
      return Transaction(
        id = null,
        accountId = null,
        totalAmount = null,
        mcc = mcc,
        merchant = null,
        code = null,
      )
    }

    @JvmStatic
    fun provideTransactionToCheckMcc(): List<Arguments> {
      return listOf(
        // by merchant
        Arguments.of(withMerchant("unknown"), Mcc.CASH),
        Arguments.of(withMerchant(Merchant.UBER_EATS.title), Mcc.MEAL),
        Arguments.of(withMerchant(Merchant.MART.title), Mcc.FOOD),
        Arguments.of(withMerchant(null), Mcc.CASH),

        // by mcc
        Arguments.of(withMcc("5411"), Mcc.FOOD),
        Arguments.of(withMcc("5412"), Mcc.FOOD),
        Arguments.of(withMcc("5811"), Mcc.MEAL),
        Arguments.of(withMcc("5812"), Mcc.MEAL),
        Arguments.of(withMcc("unknown"), Mcc.CASH),
        Arguments.of(withMcc(null), Mcc.CASH),
      )
    }
  }

}