package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import java.math.BigDecimal

class AccountResponse (
  val id: String,
  val food: BigDecimal,
  val meal: BigDecimal,
  val cash: BigDecimal,
) {
  companion object {
    fun fromDomain(account: Account): AccountResponse {
      return AccountResponse(
        id = account.id!!,
        food = account.food,
        meal = account.meal,
        cash = account.cash,
      )
    }
  }
}