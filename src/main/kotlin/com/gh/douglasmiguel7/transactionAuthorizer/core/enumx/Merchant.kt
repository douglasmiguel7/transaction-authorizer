package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.FOOD
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.MEAL

// can be loaded by environment variable in the future
enum class Merchant(
  val title: String,
  val mcc: Mcc,
) {
  UBER_TRIP(title = "trip", mcc = CASH),
  UBER_EATS(title = "eats", mcc = MEAL),
  PICPAY(title = "picpay", mcc = CASH),
  PAG(title = "pag", mcc = CASH),
  MART(title = "mercado", mcc = FOOD),
  RESTAURANT(title = "restaurante", mcc = MEAL);

  companion object {
    fun getMerchantMccByTitle(searchString: String): Mcc? {
      return entries.find {
        entry -> entry.title.contains(searchString)
      }?.mcc
    }
  }
}