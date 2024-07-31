package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.output.DeleteAccountOutput
import java.util.UUID

class DeleteAccountUseCase(
  private val deleteAccountOutput: DeleteAccountOutput
) : DeleteAccountInput {
  override fun delete(id: UUID) {
    deleteAccountOutput.delete(id)
  }
}