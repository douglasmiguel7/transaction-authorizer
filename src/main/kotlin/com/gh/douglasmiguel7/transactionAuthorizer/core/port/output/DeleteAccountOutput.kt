package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import java.util.UUID

interface DeleteAccountOutput {
  fun delete(id: UUID)
}