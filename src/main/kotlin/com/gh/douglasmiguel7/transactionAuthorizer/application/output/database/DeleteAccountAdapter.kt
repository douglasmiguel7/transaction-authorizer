package com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database

import com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.repository.AccountRepository
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.DeleteAccountOutput
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