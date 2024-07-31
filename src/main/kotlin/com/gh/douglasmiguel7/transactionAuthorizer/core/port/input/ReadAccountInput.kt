package com.gh.douglasmiguel7.transactionAuthorizer.core.port.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface ReadAccountInput {
  fun read(): List<Account>
}