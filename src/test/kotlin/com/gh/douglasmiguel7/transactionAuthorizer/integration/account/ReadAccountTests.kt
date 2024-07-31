package com.gh.douglasmiguel7.transactionAuthorizer.integration.account

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class ReadAccountTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TestComponent,
  @Autowired val repository: AccountRepository,
) {

  @Test
  fun `should get account`() {
    val entity = testComponent.accountEntity()

    mockMvc.perform(get("/accounts"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isArray)
      .andExpect(jsonPath("$[0].id").value(entity.id.toString()))
      .andExpect(jsonPath("$[0].food").value(entity.food))
      .andExpect(jsonPath("$[0].meal").value(entity.meal))
      .andExpect(jsonPath("$[0].cash").value(entity.cash))

    repository.deleteAll()
  }

}