package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input

import java.util.UUID

interface DeleteAccountInput {
  fun delete(id: UUID)
}