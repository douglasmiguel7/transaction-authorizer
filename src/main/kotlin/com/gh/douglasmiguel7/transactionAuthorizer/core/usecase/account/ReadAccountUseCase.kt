package com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Account
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import java.util.UUID

class ReadAccountUseCase(
  private val readAccountOutput: ReadAccountOutput
) : ReadAccountInput {
  override fun read(): List<Account> {
    return readAccountOutput.read()
  }

  override fun readById(id: UUID): Account {
    return readAccountOutput.readById(id)
  }
}