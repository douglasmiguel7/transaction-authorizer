package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import java.util.UUID

interface ReadAccountOutput {
  fun read(): List<Account>
  fun getById(id: UUID): Account
}