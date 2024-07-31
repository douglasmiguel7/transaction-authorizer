package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input

import java.util.UUID

interface DeleteAccountInput {
  fun delete(id: UUID)
}