package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.TransactionRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response.CreateTransactionResponse
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response.ReadTransactionResponse
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.ReadTransactionInput
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(
  val createTransactionInput: CreateTransactionInput,
  val readTransactionInput: ReadTransactionInput,
) {

  @PostMapping
  fun create(@RequestBody transactionRequest: TransactionRequest) : CreateTransactionResponse {
    val account = createTransactionInput.create(transactionRequest.toDomain())

    return CreateTransactionResponse.fromDomain(account)
  }

  @GetMapping
  fun read() : List<ReadTransactionResponse> {
    return readTransactionInput.read().map(ReadTransactionResponse::fromDomain)
  }
}
