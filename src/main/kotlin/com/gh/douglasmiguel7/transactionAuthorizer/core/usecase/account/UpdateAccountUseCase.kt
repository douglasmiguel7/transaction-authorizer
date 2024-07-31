package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.UpdateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.UpdateAccountOutput

class UpdateAccountUseCase(
  private val updateAccountOutput: UpdateAccountOutput,
) : UpdateAccountInput {
  override fun update(account: Account): Account {
    return updateAccountOutput.update(account)
  }
}