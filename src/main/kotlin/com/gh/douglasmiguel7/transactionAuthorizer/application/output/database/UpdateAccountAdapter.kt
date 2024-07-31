package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database

import com.gh.douglasmiguel7.transactionAuthorizer.application.exception.AccountNotFound
import com.gh.douglasmiguel7.transactionAuthorizer.application.extension.toUUID
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.output.UpdateAccountOutput
import org.springframework.stereotype.Component

@Component
class UpdateAccountAdapter(
  private val repository: AccountRepository,
) : UpdateAccountOutput {
  override fun update(account: Account): Account {
    val optional = repository.findById(account.id!!.toUUID())

    if (optional.isEmpty) {
      throw AccountNotFound()
    }

    val entity = optional.get().copy(
      food = account.food,
      meal = account.meal,
      cash = account.cash
    )

    return repository.save(entity).toDomain()
  }
}