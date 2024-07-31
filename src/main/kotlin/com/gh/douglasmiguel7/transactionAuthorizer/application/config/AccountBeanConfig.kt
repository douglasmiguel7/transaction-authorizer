package com.gh.douglasmiguel7.transactionAuthorizer.application.config

import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.input.UpdateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.DeleteAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.UpdateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account.CreateAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account.DeleteAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account.ReadAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.account.UpdateAccountUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccountBeanConfig {

  @Bean
  fun createAccountInput(createAccountOutput: CreateAccountOutput): CreateAccountInput {
    return CreateAccountUseCase(createAccountOutput)
  }

  @Bean
  fun readAccountInput(readAccountOutput: ReadAccountOutput): ReadAccountInput {
    return ReadAccountUseCase(readAccountOutput)
  }

  @Bean
  fun updateAccountInput(updateAccountOutput: UpdateAccountOutput): UpdateAccountInput {
    return UpdateAccountUseCase(updateAccountOutput)
  }

  @Bean
  fun deleteAccountInput(deleteAccountOutput: DeleteAccountOutput): DeleteAccountInput {
    return DeleteAccountUseCase(deleteAccountOutput)
  }

}