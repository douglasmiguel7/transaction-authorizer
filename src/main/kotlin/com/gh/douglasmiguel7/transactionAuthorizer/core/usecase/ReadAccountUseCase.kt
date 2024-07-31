package com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.ReadAccountOutput

class ReadAccountUseCase(
  private val readAccountOutput: ReadAccountOutput
) : ReadAccountInput {
  override fun read(): List<Account> {
    return readAccountOutput.read()
  }
}