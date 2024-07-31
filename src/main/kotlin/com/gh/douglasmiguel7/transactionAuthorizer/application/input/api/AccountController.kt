package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api

import com.gh.douglasmiguel7.transactionAuthorizer.application.extension.toUUID
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.request.AccountRequest
import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response.AccountResponse
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.UpdateAccountInput
import org.hibernate.validator.constraints.UUID
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
  val createAccountInput: CreateAccountInput,
  val readAccountInput: ReadAccountInput,
  val updateAccountInput: UpdateAccountInput,
  val deleteAccountInput: DeleteAccountInput,
) {

  @PostMapping
  @ResponseStatus(CREATED)
  fun create(@RequestBody accountRequest: AccountRequest) : AccountResponse {
    val account = createAccountInput.create(accountRequest.toDomain())

    return AccountResponse.fromDomain(account)
  }

  @GetMapping
  fun read() : List<AccountResponse> {
    return readAccountInput.read().map(AccountResponse::fromDomain)
  }

  @PutMapping("/{id}")
  fun update(@PathVariable id: String, @RequestBody accountRequest: AccountRequest) : ResponseEntity<AccountResponse> {
    val account = updateAccountInput.update(accountRequest.toDomainWithId(id)) ?: return ResponseEntity.notFound().build()

    return ResponseEntity.ok(AccountResponse.fromDomain(account))
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable @UUID id: String) {
    deleteAccountInput.delete(id.toUUID())
  }
}
