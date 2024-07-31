package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.output.ReadAccountOutput

class ReadAccountUseCase(
  private val readAccountOutput: ReadAccountOutput
) : ReadAccountInput {
  override fun read(): List<Account> {
    return readAccountOutput.read()
  }
}