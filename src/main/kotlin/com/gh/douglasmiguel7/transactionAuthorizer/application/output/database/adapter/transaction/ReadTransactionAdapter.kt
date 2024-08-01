package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.adapter.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.TransactionEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.TransactionRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.ReadTransactionOutput
import org.springframework.stereotype.Component

@Component
class ReadTransactionAdapter(
  private val repository: TransactionRepository,
) : ReadTransactionOutput {
  override fun read(): List<Transaction> {
    return repository.findAll().map(TransactionEntity::toDomain)
  }
}