package com.gh.douglasmiguel7.transactionAuthorizer.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.gh.douglasmiguel7.transactionAuthorizer.account.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.account.application.input.api.response.AccountResponse
import java.math.BigDecimal
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class AccountControllerTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val objectMapper: ObjectMapper,
) {

  val request = AccountRequest(
    food = BigDecimal.ZERO,
    meal = BigDecimal.ZERO,
    cash = BigDecimal.ZERO,
  )

  val response = AccountResponse(
    id = "abc",
    food = BigDecimal.ZERO,
    meal = BigDecimal.ZERO,
    cash = BigDecimal.ZERO,
  )

  @Test
  fun `should create account`() {
    val requestBody = objectMapper.writeValueAsString(request)

    mockMvc.perform(post("/accounts").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").isNotEmpty)
      .andExpect(jsonPath("$.food").value(request.food))
      .andExpect(jsonPath("$.meal").value(request.meal))
      .andExpect(jsonPath("$.cash").value(request.cash))
  }

  @Test
  fun `should get account`() {
    mockMvc.perform(get("/accounts"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isArray)
      .andExpect(jsonPath("$[0].id").value(response.id))
      .andExpect(jsonPath("$[0].food").value(response.food))
      .andExpect(jsonPath("$[0].meal").value(response.meal))
      .andExpect(jsonPath("$[0].cash").value(response.cash))
  }


  @Test
  fun `should update account`() {
    mockMvc.perform(put("/accounts"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").isNotEmpty)
      .andExpect(jsonPath("$.food").value(response.food))
      .andExpect(jsonPath("$.meal").value(response.meal))
      .andExpect(jsonPath("$.cash").value(response.cash))
  }

  @Test
  fun `should dele account`() {
    mockMvc.perform(delete("/accounts"))
      .andExpect(status().isOk)
  }
}