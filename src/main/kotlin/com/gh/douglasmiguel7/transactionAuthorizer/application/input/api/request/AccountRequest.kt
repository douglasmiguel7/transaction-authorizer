package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

data class AccountRequest (
  @field:PositiveOrZero
  val food: BigDecimal,

  @field:PositiveOrZero
  val meal: BigDecimal,

  @field:PositiveOrZero
  val cash: BigDecimal,
) {
  fun toDomain(): Account {
    return Account(
      id = null,
      food = food,
      meal = meal,
      cash = cash,
    )
  }

  fun toDomainWithId(id: String): Account {
    return Account(
      id = id,
      food = food,
      meal = meal,
      cash = cash,
    )
  }
}