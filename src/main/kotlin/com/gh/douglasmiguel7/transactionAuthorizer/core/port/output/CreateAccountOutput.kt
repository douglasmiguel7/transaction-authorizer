package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account

interface CreateAccountOutput {
  fun create(account: Account): Account
}