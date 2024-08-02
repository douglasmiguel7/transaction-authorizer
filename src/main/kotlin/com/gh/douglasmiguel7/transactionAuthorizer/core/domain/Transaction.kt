package com.gh.douglasmiguel7.transactionAuthorizer.core.domain

import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.APPROVED
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.NOT_ENOUGH_FUNDS
import java.math.BigDecimal
import java.util.UUID

data class Transaction(
  val id: UUID?,
  val accountId: UUID,
  val totalAmount: BigDecimal,
  val mcc: String,
  val merchant: String,
  val code: String?,
) {
  fun withMcc(mcc: Mcc): Transaction {
    return copy(
      mcc = mcc.name
    )
  }

  fun rejectWithNotEnoughFundsCode(): Transaction {
    return copy(
      code = NOT_ENOUGH_FUNDS.code
    )
  }

  fun approve(): Transaction {
    return copy(
      code = APPROVED.code,
    )
  }
}