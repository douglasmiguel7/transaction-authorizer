package com.gh.douglasmiguel7.transactionAuthorizer.unit

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.CASH
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.FOOD
import com.gh.douglasmiguel7.transactionAuthorizer.core.enumx.Mcc.MEAL
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AccountTests {

  val account = Account(
    id = null,
    food = BigDecimal("10"),
    meal = BigDecimal("10"),
    cash = BigDecimal("10"),
  )

  @ParameterizedTest
  @MethodSource("provideAccountToCheckFunds")
  fun `should return true or false when check funds as expected`(mcc: Mcc, amount: String, expectedResult: Boolean) {
    val result = account.hasFundsByMcc(mcc, BigDecimal(amount))

    assertEquals(expectedResult, result)
  }

  @ParameterizedTest
  @MethodSource("provideAccountToCheckDebit")
  fun `should debit as expected`(mcc: Mcc, amount: String, expectedFood: String, expectedMeal: String, expectedCash: String) {
    val result = account.debit(mcc, BigDecimal(amount))

    assertEquals(BigDecimal(expectedFood), result.food)
    assertEquals(BigDecimal(expectedMeal), result.meal)
    assertEquals(BigDecimal(expectedCash), result.cash)
  }

  companion object {
    @JvmStatic
    fun provideAccountToCheckFunds(): List<Arguments> {
      return listOf(
        Arguments.of(FOOD, "-1", true),
        Arguments.of(FOOD, "0", true),
        Arguments.of(FOOD, "20", true),
        Arguments.of(FOOD, "30", false),
        Arguments.of(FOOD, "-30", false),

        Arguments.of(MEAL, "-1", true),
        Arguments.of(MEAL, "0", true),
        Arguments.of(MEAL, "20", true),
        Arguments.of(MEAL, "30", false),
        Arguments.of(MEAL, "-30", false),

        Arguments.of(CASH, "-1", true),
        Arguments.of(CASH, "0", true),
        Arguments.of(CASH, "10", true),
        Arguments.of(CASH, "30", false),
        Arguments.of(CASH, "-30", false),
      )
    }

    @JvmStatic
    fun provideAccountToCheckDebit(): List<Arguments> {
      return listOf(
        Arguments.of(FOOD, "-1", "9", "10", "10"),
        Arguments.of(FOOD, "0", "10", "10", "10"),
        Arguments.of(FOOD, "10", "0", "10", "10"),
        Arguments.of(FOOD, "20", "0", "10", "0"),
        Arguments.of(FOOD, "30", "10", "10", "10"),

        Arguments.of(MEAL, "-1", "10", "9", "10"),
        Arguments.of(MEAL, "0", "10", "10", "10"),
        Arguments.of(MEAL, "10", "10", "0", "10"),
        Arguments.of(MEAL, "20", "10", "0", "0"),
        Arguments.of(MEAL, "30", "10", "10", "10"),

        Arguments.of(CASH, "-1", "10", "10", "9"),
        Arguments.of(CASH, "0", "10", "10", "10"),
        Arguments.of(CASH, "1", "10", "10", "9"),
        Arguments.of(CASH, "10", "10", "10", "0"),
        Arguments.of(CASH, "20", "10", "10", "10"),
      )
    }
  }

}