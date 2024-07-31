package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.adapter.account

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import org.springframework.stereotype.Component

@Component
class ReadAccountAdapter(
  private val repository: AccountRepository,
) : ReadAccountOutput {
  override fun read(): List<Account> {
    return repository.findAll().map(AccountEntity::toDomain)
  }
}