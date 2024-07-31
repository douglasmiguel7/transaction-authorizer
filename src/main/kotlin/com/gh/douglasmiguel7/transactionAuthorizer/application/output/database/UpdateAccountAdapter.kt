package com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database

import com.gh.douglasmiguel7.transactionAuthorizer.account.application.extension.toUUID
import com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.UpdateAccountOutput
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.NotFound

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