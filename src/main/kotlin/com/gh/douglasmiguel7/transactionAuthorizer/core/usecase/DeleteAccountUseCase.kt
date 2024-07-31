package com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.DeleteAccountOutput
import java.util.UUID

class DeleteAccountUseCase(
  private val deleteAccountOutput: DeleteAccountOutput
) : DeleteAccountInput {
  override fun delete(id: UUID) {
    deleteAccountOutput.delete(id)
  }
}