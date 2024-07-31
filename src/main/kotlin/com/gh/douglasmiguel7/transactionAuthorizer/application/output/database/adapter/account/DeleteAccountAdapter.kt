package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.adapter.account

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.DeleteAccountOutput
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class DeleteAccountAdapter(
  private val repository: AccountRepository,
): DeleteAccountOutput {
  override fun delete(id: UUID) {
    repository.deleteById(id)
  }
}