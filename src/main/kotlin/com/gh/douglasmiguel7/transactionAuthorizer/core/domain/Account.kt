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
    val amountAsPositive = amount.abs()

    return when(mcc) {
      FOOD -> food.add(cash).minus(amountAsPositive) >= BigDecimal.ZERO
      MEAL -> meal.add(cash).minus(amountAsPositive) >= BigDecimal.ZERO
      CASH -> cash.minus(amountAsPositive) >= BigDecimal.ZERO
    }
  }

  fun debit(mcc: Mcc, amount: BigDecimal): Account {
    val amountAsPositive = amount.abs()

    if (!hasFundsByMcc(mcc = mcc, amount = amountAsPositive)) {
      return this
    }

    if (amountAsPositive == BigDecimal.ZERO) {
      return this
    }

    return when(mcc) {
      FOOD ->
        if (food > amountAsPositive)
          copy(food = food.minus(amountAsPositive))
        else
          copy(food = BigDecimal.ZERO)
            .debit(mcc = CASH, amount = amountAsPositive.minus(food))

      MEAL ->
        if (meal > amountAsPositive)
          copy(meal = meal.minus(amountAsPositive))
        else
          copy(meal = BigDecimal.ZERO)
            .debit(mcc = CASH, amount = amountAsPositive.minus(meal))

      CASH -> copy(cash = cash.minus(amountAsPositive))
    }
  }
}