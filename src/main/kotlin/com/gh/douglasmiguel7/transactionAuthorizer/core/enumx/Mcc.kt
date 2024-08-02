package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

// can be loaded by environment variable in the future
enum class Mcc(
  val codes: List<String> = listOf(),
) {
  FOOD(codes = listOf("5411", "5412")),
  MEAL(codes = listOf("5811", "5812")),
  CASH;

  companion object {
    fun getMccByCode(searchString: String): Mcc? {
      return entries.find {
        entry -> entry.codes.any { code -> code == searchString }
      }
    }
  }
}