package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput

class CreateTransactionUseCase(
  private val createTransactionOutput: CreateTransactionOutput,
) : CreateTransactionInput {
  override fun create(transaction: Transaction): Transaction {
    return createTransactionOutput.create(transaction.copy(code = "00"))
  }

}