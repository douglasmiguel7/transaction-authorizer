package com.gh.douglasmiguel7.transactionAuthorizer.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface ReadAccountOutput {
  fun read(): List<Account>
}