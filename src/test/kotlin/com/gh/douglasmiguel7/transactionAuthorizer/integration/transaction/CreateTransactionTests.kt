package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.TransactionRepository
import org.junit.jupiter.api.Test
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
class CreateTransactionTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TransactionTestComponent,
  @Autowired val repository: TransactionRepository,
) {

  @Test
  fun `should create transaction`() {
    val request = testComponent.transactionRequest()
    val requestBody = jacksonObjectMapper().writeValueAsString(request)

    mockMvc.perform(post("/transactions").content(requestBody).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.code").value("00"))

    repository.deleteAll()
  }
}