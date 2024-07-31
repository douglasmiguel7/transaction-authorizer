package com.gh.douglasmiguel7.transactionAuthorizer.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface UpdateAccountOutput {
  fun update(account: Account): Account
}