package com.gh.douglasmiguel7.transactionAuthorizer.integration

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class DeleteAccountTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TestComponent,
  @Autowired val repository: AccountRepository,
) {

  @Test
  fun `should delete account`() {
    val id = testComponent.accountId()

    mockMvc.perform(delete("/accounts/${id}"))
      .andExpect(status().isOk)

    repository.deleteAll()
  }

  @Test
  fun `should fail if id has invalid format`() {
    val id = "invalidIdFormat"

    mockMvc.perform(delete("/accounts/${id}")).andExpect(status().isBadRequest)
  }
}