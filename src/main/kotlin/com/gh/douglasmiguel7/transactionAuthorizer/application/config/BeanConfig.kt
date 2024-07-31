package com.gh.douglasmiguel7.transactionAuthorizer.account.application.config

import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.CreateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.DeleteAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.ReadAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.input.UpdateAccountInput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.CreateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.DeleteAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.ReadAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.port.output.UpdateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase.CreateAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase.DeleteAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase.ReadAccountUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.usecase.UpdateAccountUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

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