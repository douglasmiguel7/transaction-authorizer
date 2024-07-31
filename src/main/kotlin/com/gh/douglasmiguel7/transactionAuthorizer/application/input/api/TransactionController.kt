package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response.AccountResponse
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.ReadAccountInput
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(
  val createAccountInput: CreateAccountInput,
  val readAccountInput: ReadAccountInput,
) {

  @PostMapping
  fun create(@RequestBody @Valid accountRequest: AccountRequest) : AccountResponse {
    val account = createAccountInput.create(accountRequest.toDomain())

    return AccountResponse.fromDomain(account)
  }

  @GetMapping
  fun read() : List<AccountResponse> {
    return readAccountInput.read().map(AccountResponse::fromDomain)
  }
}
