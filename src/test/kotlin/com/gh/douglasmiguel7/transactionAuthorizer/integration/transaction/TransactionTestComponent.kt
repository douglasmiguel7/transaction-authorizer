package com.gh.douglasmiguel7.transactionAuthorizer.integration.transaction

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.TransactionRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.TransactionEntity
import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.TransactionRepository
import java.math.BigDecimal
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class TransactionTestComponent(
  val repository: TransactionRepository,
) {

  fun transactionRequest(): TransactionRequest {
    return TransactionRequest(
      accountId = UUID.randomUUID(),
      totalAmount = BigDecimal.TEN,
      mcc = "5411",
      merchant = "Mercadinho Power"
    )
  }

  fun transactionEntity(): TransactionEntity {
    return repository.save(
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
}