package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account

interface UpdateAccountInput {
  fun update(account: Account): Account?
}