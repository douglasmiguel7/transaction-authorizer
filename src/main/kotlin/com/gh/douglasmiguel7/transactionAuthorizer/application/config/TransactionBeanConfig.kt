package com.gh.douglasmiguel7.transactionAuthorizer.application.config

import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.CreateAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.account.output.ReadAccountOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.ReadTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.ReadTransactionOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction.CreateTransactionUseCase
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction.ReadTransactionUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransactionBeanConfig {

  @Bean
  fun createTransactionInput(
    createTransactionOutput: CreateTransactionOutput,
    createAccountOutput: CreateAccountOutput,
    readAccountOutput: ReadAccountOutput,
  ): CreateTransactionInput {
    return CreateTransactionUseCase(
      createTransactionOutput,
      readAccountOutput,
      createAccountOutput,
    )
  }

  @Bean
  fun readTransactionInput(readTransactionOutput: ReadTransactionOutput): ReadTransactionInput {
    return ReadTransactionUseCase(readTransactionOutput)
  }

}