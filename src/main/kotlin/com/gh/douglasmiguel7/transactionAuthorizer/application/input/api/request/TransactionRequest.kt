package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import java.math.BigDecimal
import java.util.UUID

data class TransactionRequest (
  val accountId: UUID?,
  val totalAmount: BigDecimal?,
  val mcc: String?,
  val merchant: String?,
) {
  fun toDomain(): Transaction {
    return Transaction(
      id = null,
      accountId = accountId,
      totalAmount = totalAmount,
      mcc = mcc,
      merchant = merchant,
      code = null,
    )
  }
}