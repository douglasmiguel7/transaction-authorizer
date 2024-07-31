package com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database

import com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.ReadAccountOutput
import org.springframework.stereotype.Component

@Component
class ReadAccountAdapter(
  private val repository: AccountRepository,
) : ReadAccountOutput {
  override fun read(): List<Account> {
    return repository.findAll().map(AccountEntity::toDomain)
  }
}