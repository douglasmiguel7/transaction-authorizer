package com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain

import java.math.BigDecimal

data class Account(
  val id: String?,
  val food: BigDecimal,
  val meal: BigDecimal,
  val cash: BigDecimal,
)