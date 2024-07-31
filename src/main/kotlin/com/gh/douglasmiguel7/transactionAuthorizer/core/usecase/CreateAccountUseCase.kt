package com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.CreateAccountOutput

class CreateAccountUseCase(
  private val createAccountOutput: CreateAccountOutput
) : CreateAccountInput {
  override fun create(account: Account): Account {
    return createAccountOutput.create(account)
  }
}