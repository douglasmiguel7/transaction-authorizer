package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.adapter.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.TransactionEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.TransactionRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput
import org.springframework.stereotype.Component

@Component
class CreateTransactionAdapter(
  private val repository: TransactionRepository,
) : CreateTransactionOutput {
  override fun create(transaction: Transaction): Transaction {
    return repository.save(TransactionEntity.fromDomain(transaction)).toDomain()
  }
}