package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account

interface ReadAccountOutput {
  fun read(): List<Account>
}