package com.gh.douglasmiguel7.transactionAuthorizer.core.enumx

import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.FOOD
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.MEAL

enum class Merchant(
  val title: String,
  val mcc: Mcc,
) {
  UBER_TRIP(title = "uber trip", mcc = CASH),
  UBER_EATS(title = "uber eats", mcc = MEAL),
  PICPAY(title = "picpay", mcc = CASH),
  PAG(title = "pag", mcc = CASH),
  MART(title = "mercado", mcc = FOOD),
  RESTAURANT(title = "restaurante", mcc = MEAL),
}