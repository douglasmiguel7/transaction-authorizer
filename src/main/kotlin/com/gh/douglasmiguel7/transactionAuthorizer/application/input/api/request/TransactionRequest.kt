package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.validator.AccountExists
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal
import java.util.UUID

data class TransactionRequest (
  @AccountExists
  val accountId: UUID,

  @field:PositiveOrZero
  val totalAmount: BigDecimal,

  @field:NotBlank
  val mcc: String,

  @field:NotBlank
  val merchant: String,
)