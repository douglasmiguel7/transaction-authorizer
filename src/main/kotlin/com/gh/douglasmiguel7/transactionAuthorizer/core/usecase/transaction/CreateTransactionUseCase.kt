package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.FOOD
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.MEAL
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Merchant
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.APPROVED
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.TransactionCode.NOT_ENOUGH_FUNDS
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput
import java.math.BigDecimal

class CreateTransactionUseCase(
  private val createTransactionOutput: CreateTransactionOutput,
  private val readAccouOutput: ReadAccountOutput,
  private val createAccountOutput: CreateAccountOutput,
) : CreateTransactionInput {

  private fun getMcc(transaction: Transaction): Mcc {
    var mcc = Merchant.entries.find { entry -> entry.title.contains(transaction.merchant) }?.mcc

    if (mcc != null) {
      return mcc
    }

    mcc = Mcc.entries.find { entry -> entry.codes.any { code -> code == transaction.mcc } }

    return  mcc ?: CASH
  }

  private fun hasFunds(mcc: Mcc, transaction: Transaction): Boolean {
    val account = readAccouOutput.getById(transaction.accountId)

    return when(mcc) {
      FOOD -> account.run { food.add(cash).minus(transaction.totalAmount) >= BigDecimal.ZERO }
      MEAL -> account.run { meal.add(cash).minus(transaction.totalAmount) >= BigDecimal.ZERO }
      CASH -> account.run { cash.minus(transaction.totalAmount) >= BigDecimal.ZERO }
    }
  }

  private fun updateFunds(
    mcc: Mcc,
    food: BigDecimal,
    meal: BigDecimal,
    cash: BigDecimal,
    totalAmount: BigDecimal,
  ): Triple<BigDecimal, BigDecimal, BigDecimal> {
    if (totalAmount == BigDecimal.ZERO) {
      return Triple(food, meal, cash)
    }

    return when(mcc) {
      FOOD ->
        if (food > totalAmount)
          Triple(food.minus(totalAmount), meal, cash)
        else updateFunds(
          mcc = CASH,
          food = BigDecimal.ZERO,
          meal = meal,
          cash = cash,
          totalAmount = totalAmount.minus(food)
        )

      MEAL ->
        if (meal > totalAmount)
          Triple(food, meal.minus(totalAmount), cash)
        else updateFunds(
          mcc = CASH,
          food = food,
          meal = BigDecimal.ZERO,
          cash = cash,
          totalAmount = totalAmount.minus(food)
        )

      CASH -> Triple(food, meal, cash.minus(totalAmount))
    }
  }

  override fun create(transaction: Transaction): Transaction {
    val mcc = getMcc(transaction)

    val hasFunds = hasFunds(mcc, transaction)

    if (!hasFunds) {
      return createTransactionOutput.create(transaction.copy(mcc = mcc.name, code = NOT_ENOUGH_FUNDS.code))
    }

    val account = readAccouOutput.getById(transaction.accountId)

    val (food, meal, cash) = updateFunds(
      mcc = mcc,
      food = account.food,
      meal = account.meal,
      cash = account.cash,
      totalAmount = transaction.totalAmount,
    )

    createAccountOutput.create(account.copy(food = food, meal = meal, cash = cash))

    return createTransactionOutput.create(transaction.copy(mcc = mcc.name, code = APPROVED.code))
  }

}