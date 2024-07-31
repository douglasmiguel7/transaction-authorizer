package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database

import com.gh.douglasmiguel7.transactionAuthorizer.application.extension.toUUID
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.output.UpdateAccountOutput
import org.springframework.stereotype.Component

@Component
class UpdateAccountAdapter(
  private val repository: AccountRepository,
) : UpdateAccountOutput {
  override fun update(account: Account): Account? {
    var entity: Account? = null

    repository.findById(account.id!!.toUUID()).ifPresent {
      val updatedEntity = it.copy(
        food = account.food,
        meal = account.meal,
        cash = account.cash
      )

      entity = repository.save(updatedEntity).toDomain()
    }

    return entity
  }
}