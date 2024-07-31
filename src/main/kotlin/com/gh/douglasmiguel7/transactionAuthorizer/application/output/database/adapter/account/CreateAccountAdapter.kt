package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.adapter.account

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput
import org.springframework.stereotype.Component

@Component
class CreateAccountAdapter(
  private val repository: AccountRepository,
) : CreateAccountOutput {
  override fun create(account: Account): Account {
    return repository.save(AccountEntity.fromDomain(account)).toDomain()
  }
}