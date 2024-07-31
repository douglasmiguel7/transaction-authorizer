package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account

interface CreateAccountInput {
  fun create(account: Account): Account
}