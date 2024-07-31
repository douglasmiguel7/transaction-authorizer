package com.gh.douglasmiguel7.transactionAuthorizer.core.port.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface CreateAccountInput {
  fun create(account: Account): Account
}