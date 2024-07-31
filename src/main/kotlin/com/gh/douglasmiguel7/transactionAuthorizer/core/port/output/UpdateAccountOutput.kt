package com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account

interface UpdateAccountOutput {
  fun update(account: Account): Account?
}