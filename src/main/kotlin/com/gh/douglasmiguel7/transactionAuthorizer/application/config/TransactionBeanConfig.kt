package com.gh.douglasmiguel7.transactionAuthorizer.application.config

import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input.CreateTransactionInput
import com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output.CreateTransactionOutput
import com.gh.douglasmiguel7.transactionAuthorizer.core.usecase.transaction.CreateTransactionUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransactionBeanConfig {

  @Bean
  fun createTransactionInput(createTransactionOutput: CreateTransactionOutput): CreateTransactionInput {
    return CreateTransactionUseCase(createTransactionOutput)
  }

}