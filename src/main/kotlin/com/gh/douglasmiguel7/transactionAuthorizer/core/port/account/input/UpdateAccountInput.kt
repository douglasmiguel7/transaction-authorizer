package com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account

interface UpdateAccountInput {
  fun update(account: Account): Account
}