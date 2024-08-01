package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import java.math.BigDecimal
import java.util.UUID

class CreateTransactionResponse (
  val code: String,
) {
  companion object {
    fun fromDomain(transaction: Transaction): CreateTransactionResponse {
      return CreateTransactionResponse(
        code = transaction.code!!,
      )
    }
  }
}

class ReadTransactionResponse (
  val id: UUID,
  val accountId: UUID,
  val totalAmount: BigDecimal,
  val mcc: String,
  val merchant: String,
  val code: String,
) {
  companion object {
    fun fromDomain(transaction: Transaction): ReadTransactionResponse {
      return ReadTransactionResponse(
        id = transaction.id!!,
        accountId = transaction.accountId,
        totalAmount = transaction.totalAmount,
        mcc = transaction.mcc,
        merchant = transaction.merchant,
        code = transaction.code!!,
      )
    }
  }
}