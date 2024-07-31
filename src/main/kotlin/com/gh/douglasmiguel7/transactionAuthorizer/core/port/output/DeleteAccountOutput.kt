package com.gh.douglasmiguel7.transactionAuthorizer.core.port.output

import java.util.UUID

interface DeleteAccountOutput {
  fun delete(id: UUID)
}