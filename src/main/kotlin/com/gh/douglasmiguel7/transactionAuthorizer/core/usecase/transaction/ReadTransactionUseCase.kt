package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.ReadTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.ReadTransactionOutput

class ReadTransactionUseCase(
  private val readTransactionOutput: ReadTransactionOutput
) : ReadTransactionInput {
  override fun read(): List<Transaction> {
    return readTransactionOutput.read()
  }
}