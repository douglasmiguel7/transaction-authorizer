package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

enum class Mcc(
  val codes: List<String>,
) {
  FOOD(codes = listOf("5411", "5412")),
  MEAL(codes = listOf("5811", "5812")),
  CASH(codes = listOf());

  companion object {
    fun byCode(value: String): Mcc {
      val mcc = entries.find { entry ->
        entry.codes.any { code -> code == value }
      }

      return mcc ?: CASH
    }
  }
}