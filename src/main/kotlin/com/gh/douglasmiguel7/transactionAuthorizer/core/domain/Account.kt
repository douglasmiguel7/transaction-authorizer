package com.gh.douglasmiguel7.transactionAuthorizer.core.domain

import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.FOOD
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.MEAL
import java.math.BigDecimal

data class Account(
  val id: String?,
  val food: BigDecimal,
  val meal: BigDecimal,
  val cash: BigDecimal,
) {
  fun hasFundsByMcc(mcc: Mcc, amount: BigDecimal): Boolean {
    return when(mcc) {
      FOOD -> food.add(cash).minus(amount) >= BigDecimal.ZERO
      MEAL -> meal.add(cash).minus(amount) >= BigDecimal.ZERO
      CASH -> cash.minus(amount) >= BigDecimal.ZERO
    }
  }

  fun debit(mcc: Mcc, amount: BigDecimal): Account {
    if (amount == BigDecimal.ZERO) {
      return this
    }

    return when(mcc) {
      FOOD ->
        if (food > amount)
          copy(food = food.minus(amount))
        else
          copy(food = BigDecimal.ZERO)
            .debit(mcc = CASH, amount = amount.minus(food))

      MEAL ->
        if (meal > amount)
          copy(meal = meal.minus(amount))
        else
          copy(meal = BigDecimal.ZERO)
            .debit(mcc = CASH, amount = amount.minus(meal))

      CASH -> copy(cash = cash.minus(amount))
    }
  }
}