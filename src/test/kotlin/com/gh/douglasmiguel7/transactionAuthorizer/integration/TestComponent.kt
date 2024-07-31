package com.gh.douglasmiguel7.transactionAuthorizer.integration

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class TestComponent(
  val repository: AccountRepository,
) {
  fun accountRequest(): AccountRequest {
    return accountRequest(
      food = BigDecimal.TEN,
      meal = BigDecimal.TEN,
      cash = BigDecimal.TEN,
    )
  }

  fun accountRequest(food: BigDecimal, meal: BigDecimal, cash: BigDecimal): AccountRequest {
    return AccountRequest(
      food = food.setScale(2, HALF_UP),
      meal = meal.setScale(2, HALF_UP),
      cash = cash.setScale(2, HALF_UP),
    )
  }

  fun accountEntity(): AccountEntity {
    return repository.save(
      AccountEntity(
        id = null,
        food = BigDecimal.TEN.setScale(2, HALF_UP),
        meal = BigDecimal.TEN.setScale(2, HALF_UP),
        cash = BigDecimal.TEN.setScale(2, HALF_UP),
      )
    )
  }

  fun accountId(): UUID {
    return accountEntity().id!!
  }
}