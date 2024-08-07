package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput

class CreateAccountUseCase(
  private val createAccountOutput: CreateAccountOutput
) : CreateAccountInput {
  override fun create(account: Account): Account {
    return createAccountOutput.create(account)
  }
}