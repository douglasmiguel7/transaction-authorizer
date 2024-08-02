package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

enum class Mcc(
  val codes: List<String> = listOf(),
) {
  FOOD(codes = listOf("5411", "5412")),
  MEAL(codes = listOf("5811", "5812")),
  CASH,
}