package com.gh.douglasmiguel7.transactionAuthorizer.unit

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.APPROVED
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.NOT_ENOUGH_FUNDS
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.UNKNOWN
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import java.math.BigDecimal
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TransactionTests {

  @Test
  fun `should update only mcc`() {
    val mcc = CASH

    val transaction = Transaction(
      id = null,
      accountId = null,
      totalAmount = null,
      mcc = null,
      merchant = null,
      code = null,
    )

    val result = transaction.withMcc(CASH)

    assertEquals(transaction.id, result.id)
    assertEquals(transaction.accountId, result.accountId)
    assertEquals(transaction.totalAmount, result.totalAmount)
    assertNotEquals(transaction.mcc, result.mcc)
    assertEquals(transaction.merchant, result.merchant)
    assertEquals(transaction.code, result.code)

    assertEquals(mcc.name, result.mcc)
  }

  @Test
  fun `should update only code to unknown`() {
    val code = UNKNOWN.code

    val transaction = Transaction(
      id = null,
      accountId = null,
      totalAmount = null,
      mcc = null,
      merchant = null,
      code = null,
    )

    val result = transaction.rejectWithUnknownCode()

    assertEquals(transaction.id, result.id)
    assertEquals(transaction.accountId, result.accountId)
    assertEquals(transaction.totalAmount, result.totalAmount)
    assertEquals(transaction.mcc, result.mcc)
    assertEquals(transaction.merchant, result.merchant)
    assertNotEquals(transaction.code, result.code)

    assertEquals(code, result.code)
  }

  @Test
  fun `should update only code to not enough funds`() {
    val code = NOT_ENOUGH_FUNDS.code

    val transaction = Transaction(
      id = null,
      accountId = null,
      totalAmount = null,
      mcc = null,
      merchant = null,
      code = null,
    )

    val result = transaction.rejectWithNotEnoughFundsCode()

    assertEquals(transaction.id, result.id)
    assertEquals(transaction.accountId, result.accountId)
    assertEquals(transaction.totalAmount, result.totalAmount)
    assertEquals(transaction.mcc, result.mcc)
    assertEquals(transaction.merchant, result.merchant)
    assertNotEquals(transaction.code, result.code)

    assertEquals(code, result.code)
  }

  @Test
  fun `should update only code to approved`() {
    val code = APPROVED.code

    val transaction = Transaction(
      id = null,
      accountId = null,
      totalAmount = null,
      mcc = null,
      merchant = null,
      code = null,
    )

    val result = transaction.approve()

    assertEquals(transaction.id, result.id)
    assertEquals(transaction.accountId, result.accountId)
    assertEquals(transaction.totalAmount, result.totalAmount)
    assertEquals(transaction.mcc, result.mcc)
    assertEquals(transaction.merchant, result.merchant)
    assertNotEquals(transaction.code, result.code)

    assertEquals(code, result.code)
  }

  @ParameterizedTest
  @MethodSource("provideTransaction")
  fun `should validate as expected`(transaction: Transaction, accountExists: Boolean, expectedResult: Boolean) {
    val readAccountOutput = Mockito.mock(ReadAccountOutput::class.java)

    transaction.accountId?.let {
      `when`(readAccountOutput.existsById(it)).thenReturn(accountExists)
    }

    val result = transaction.isValid(readAccountOutput)

    assertEquals(expectedResult, result)
  }

  companion object {
    private fun allNull(): Transaction {
      return Transaction(
        id = null,
        accountId = null,
        totalAmount = null,
        mcc = null,
        merchant = null,
        code = null,
      )
    }

    private fun withAccountId(): Transaction {
      return allNull().copy(
        accountId = UUID.randomUUID(),
      )
    }

    private fun withTotalAmount(totalAmount: String?): Transaction {
      return withAccountId().copy(
        totalAmount = totalAmount?.let { BigDecimal(totalAmount) },
      )
    }

    private fun withMcc(mcc: String?): Transaction {
      return withTotalAmount("10").copy(
        mcc = mcc,
      )
    }

    private fun withMerchant(merchant: String?): Transaction {
      return withMcc("mcc").copy(
        merchant = merchant
      )
    }

    private fun valid(): Transaction {
      return withMerchant("merchant")
    }

    @JvmStatic
    fun provideTransaction(): List<Arguments> {
      return listOf(
        Arguments.of(allNull(), false, false),
        Arguments.of(withAccountId(), false, false),
        Arguments.of(withTotalAmount(null), true, false),
        Arguments.of(withTotalAmount("-1"), true, false),
        Arguments.of(withMcc(null), true, false),
        Arguments.of(withMcc(""), true, false),
        Arguments.of(withMerchant(null), true, false),
        Arguments.of(withMerchant(""), true, false),
        Arguments.of(valid(), true, true),
      )
    }
  }

}