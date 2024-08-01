package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

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
class ReadTansactionTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val testComponent: TransactionTestComponent,
) {

  @Test
  fun `should get account`() {
    val entity = testComponent.transactionEntity()

    mockMvc.perform(get("/transactions"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isArray)
      .andExpect(jsonPath("$[0].id").value(entity.id.toString()))
      .andExpect(jsonPath("$[0].accountId").value(entity.accountId.toString()))
      .andExpect(jsonPath("$[0].totalAmount").value(entity.totalAmount))
      .andExpect(jsonPath("$[0].mcc").value(entity.mcc))
      .andExpect(jsonPath("$[0].merchant").value(entity.merchant))
      .andExpect(jsonPath("$[0].code").value(entity.code))

    testComponent.cleanDatabase()
  }

}