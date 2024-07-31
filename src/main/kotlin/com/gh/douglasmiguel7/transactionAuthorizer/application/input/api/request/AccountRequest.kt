package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import java.math.BigDecimal

data class AccountRequest (
  val food: BigDecimal,
  val meal: BigDecimal,
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