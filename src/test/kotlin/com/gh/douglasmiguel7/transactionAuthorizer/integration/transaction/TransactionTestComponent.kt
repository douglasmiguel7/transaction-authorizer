package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.TransactionRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.AccountEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.TransactionEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.TransactionRepository
import java.math.BigDecimal
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class TransactionTestComponent(
  val transactionRepository: TransactionRepository,
  val accountRepository: AccountRepository,
) {

  fun transactionRequest(accountId: UUID, totalAmount: String, mcc: String, merchant: String): TransactionRequest {
    return TransactionRequest(
      accountId = accountId,
      totalAmount = BigDecimal(totalAmount),
      mcc = mcc,
      merchant = merchant
    )
  }

  fun accountEntity(food: String, meal: String, cash: String): AccountEntity {
    return accountRepository.save(
      AccountEntity(
        id = null,
        food = BigDecimal(food),
        meal = BigDecimal(meal),
        cash = BigDecimal(cash),
      )
    )
  }

  fun transactionRequestBody(totalAmount: String, mcc: String, merchant: String, food: String, meal: String, cash: String): String {
    val entity = accountEntity(food, meal, cash)
    val request = transactionRequest(entity.id!!, totalAmount, mcc, merchant)
    return jacksonObjectMapper().writeValueAsString(request)
  }

  fun transactionEntity(): TransactionEntity {
    return transactionRepository.save(
      TransactionEntity(
        id = null,
        accountId = UUID.randomUUID(),
        totalAmount = BigDecimal("1.2"),
        mcc = "5411",
        merchant = "Lojinha Guido",
        code = "00",
      )
    )
  }

  fun cleanDatabase() {
    transactionRepository.deleteAll()
    accountRepository.deleteAll()
  }
}