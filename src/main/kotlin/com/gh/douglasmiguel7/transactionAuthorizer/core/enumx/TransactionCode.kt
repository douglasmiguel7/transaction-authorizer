package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

enum class TransactionCode(
  val code: String,
) {
  APPROVED(code = "00"),
  NOT_ENOUGH_FUNDS(code = "51"),
  UNKNOWN(code = "07"),
}