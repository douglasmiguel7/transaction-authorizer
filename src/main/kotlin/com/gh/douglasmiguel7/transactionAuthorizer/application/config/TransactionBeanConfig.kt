package com.gh.douglasmiguel7.transactionAuthorizer.application.config

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
  fun createTransactionInput(createTransactionOutput: CreateTransactionOutput): CreateTransactionInput {
    return CreateTransactionUseCase(createTransactionOutput)
  }

  @Bean
  fun readTransactionInput(readTransactionOutput: ReadTransactionOutput): ReadTransactionInput {
    return ReadTransactionUseCase(readTransactionOutput)
  }

}