package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Merchant
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput

class CreateTransactionUseCase(
  private val createTransactionOutput: CreateTransactionOutput,
  private val readAccouOutput: ReadAccountOutput,
  private val createAccountOutput: CreateAccountOutput,
) : CreateTransactionInput {

  private fun getMcc(transaction: Transaction): Mcc {
    var mcc = Merchant.getMerchantMccByTitle(transaction.merchant!!)

    if (mcc != null) {
      return mcc
    }

    mcc = Mcc.getMccByCode(transaction.mcc!!)

    return  mcc ?: CASH
  }

  override fun create(transaction: Transaction): Transaction {
    val valid = transaction.isValid(readAccouOutput)

    if (!valid) {
      return transaction.rejectWithUnknownCode()
    }

    val mcc = getMcc(transaction)

    val account = readAccouOutput.readById(transaction.accountId!!)

    if (!account.hasFundsByMcc(mcc, transaction.totalAmount!!)) {
      return createTransactionOutput.create(
        transaction.withMcc(mcc).rejectWithNotEnoughFundsCode()
      )
    }

    createAccountOutput.create(account.debit(mcc = mcc, amount = transaction.totalAmount))

    return createTransactionOutput.create(transaction.withMcc(mcc).approve())
  }

}