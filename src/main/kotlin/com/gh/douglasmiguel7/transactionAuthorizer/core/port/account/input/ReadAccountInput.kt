package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import java.util.UUID

interface ReadAccountInput {
  fun read(): List<Account>
  fun readById(id: UUID): Account
}