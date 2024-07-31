package com.gh.douglasmiguel7.transactionAuthorizer.account.application.input.api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.gh.douglasmiguel7.transactionAuthorizer.account.application.input.api.serializer.MoneySerializer
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import java.math.BigDecimal

data class AccountResponse (
  val id: String,

  @JsonSerialize(using = MoneySerializer::class)
  val food: BigDecimal,

  @JsonSerialize(using = MoneySerializer::class)
  val meal: BigDecimal,

  @JsonSerialize(using = MoneySerializer::class)
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