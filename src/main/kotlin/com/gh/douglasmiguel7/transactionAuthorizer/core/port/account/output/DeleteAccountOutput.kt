package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output

import java.util.UUID

interface DeleteAccountOutput {
  fun delete(id: UUID)
}