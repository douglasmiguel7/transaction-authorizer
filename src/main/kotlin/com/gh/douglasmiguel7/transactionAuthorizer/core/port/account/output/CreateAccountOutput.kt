package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface CreateAccountOutput {
  fun create(account: Account): Account
}