package com.gh.douglasmiguel7.transactionAuthorizer.unit

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.UpdateAccountAdapter
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.integration.account.TestComponent
import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UpdateAccountAdapterTests(
  @Autowired val repository: AccountRepository,
  @Autowired val updateAccountAdapter: UpdateAccountAdapter,
  @Autowired val testComponent: TestComponent,
) {

  @Test
  fun `should update account`() {
    val entity = testComponent.accountEntity()

    val domain = entity.toDomain().copy(
      food = BigDecimal.TEN.multiply(BigDecimal("2")).setScale(2, HALF_UP)
    )

    val updatedEntity = updateAccountAdapter.update(domain)

    assertEquals(domain.food, updatedEntity.food)
    assertEquals(domain.meal, updatedEntity.meal)
    assertEquals(domain.cash, updatedEntity.cash)

    repository.deleteAll()
  }

  @Test
  fun `should throw exception when account not found`() {
    val account = testComponent.account()

    assertThrows<Exception> {
      updateAccountAdapter.update(account)
    }
  }

}