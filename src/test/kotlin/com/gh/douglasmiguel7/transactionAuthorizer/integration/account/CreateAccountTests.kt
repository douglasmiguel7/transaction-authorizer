package com.gh.douglasmiguel7.transactionAuthorizer.integration.account

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import java.math.BigDecimal
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class CreateAccountTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TestComponent,
  @Autowired val repository: AccountRepository,
) {

  @Test
  fun `should create account`() {
    val request = testComponent.accountRequest()
    val requestBody = jacksonObjectMapper().writeValueAsString(request)

    mockMvc.perform(post("/accounts").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").isNotEmpty)
      .andExpect(jsonPath("$.food").value(request.food))
      .andExpect(jsonPath("$.meal").value(request.meal))
      .andExpect(jsonPath("$.cash").value(request.cash))

    repository.deleteAll()
  }

  @ParameterizedTest
  @MethodSource("provideFailRequestBody")
  fun `should fail if value is negative`(requestBody: String) {
    mockMvc.perform(post("/accounts").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest)
  }

  companion object {
    @JvmStatic
    fun provideFailRequestBody(): List<Arguments> {
      val foodNegative = AccountRequest(
        food = BigDecimal("-1"),
        meal = BigDecimal.TEN,
        cash = BigDecimal.TEN,
      )

      val mealNegative = AccountRequest(
        food = BigDecimal.TEN,
        meal = BigDecimal("-1"),
        cash = BigDecimal.TEN,
      )

      val cashNegative = AccountRequest(
        food = BigDecimal.TEN,
        meal = BigDecimal.TEN,
        cash = BigDecimal("-1"),
      )

      return listOf(foodNegative, mealNegative, cashNegative)
        .map(jacksonObjectMapper()::writeValueAsString)
        .map { requestBody -> Arguments.of(requestBody) }
    }
  }
}