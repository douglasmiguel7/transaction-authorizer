package com.gh.douglasmiguel7.transactionAuthorizer.integration.account

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import java.math.BigDecimal
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class AccountTestComponent(
  val repository: AccountRepository,
) {
  fun accountRequest(): AccountRequest {
    return accountRequest(
      food = BigDecimal("10.1"),
      meal = BigDecimal("0.01"),
      cash = BigDecimal("5.5"),
    )
  }

  fun accountRequest(food: BigDecimal, meal: BigDecimal, cash: BigDecimal): AccountRequest {
    return AccountRequest(
      food = food,
      meal = meal,
      cash = cash,
    )
  }

  fun accountEntity(): AccountEntity {
    return repository.save(
      AccountEntity(
        id = null,
        food = BigDecimal("0.21"),
        meal = BigDecimal("1.11"),
        cash = BigDecimal("20.0"),
      )
    )
  }

  fun accountId(): UUID {
    return accountEntity().id!!
  }

  fun account(): Account {
    return Account(
      id = UUID.randomUUID().toString(),
      food = BigDecimal("4.31"),
      meal = BigDecimal("1.21"),
      cash = BigDecimal("2.01"),
    )
  }
}