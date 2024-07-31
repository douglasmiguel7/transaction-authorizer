package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account

import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.DeleteAccountOutput
import java.util.UUID

class DeleteAccountUseCase(
  private val deleteAccountOutput: DeleteAccountOutput
) : DeleteAccountInput {
  override fun delete(id: UUID) {
    deleteAccountOutput.delete(id)
  }
}