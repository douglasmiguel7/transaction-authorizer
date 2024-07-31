package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import java.util.UUID

interface DeleteAccountInput {
  fun delete(id: UUID)
}