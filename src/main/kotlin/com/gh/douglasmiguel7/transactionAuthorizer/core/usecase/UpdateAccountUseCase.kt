package com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.UpdateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.UpdateAccountOutput

class UpdateAccountUseCase(
  private val updateAccountOutput: UpdateAccountOutput,
) : UpdateAccountInput {
  override fun update(account: Account): Account? {
    return updateAccountOutput.update(account)
  }
}