package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response

class ValidationErrorResponse(
  val property: String,
  val cause: String,
)