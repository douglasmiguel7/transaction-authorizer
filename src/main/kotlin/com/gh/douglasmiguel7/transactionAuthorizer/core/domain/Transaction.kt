package com.gh.douglasmiguel7.transactionAuthorizer.core.domain

import java.math.BigDecimal
import java.util.UUID

data class Transaction(
  val id: UUID?,
  val accountId: UUID,
  val totalAmount: BigDecimal,
  val mcc: String,
  val merchant: String,
  val code: String?,
)