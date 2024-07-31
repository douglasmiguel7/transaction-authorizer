package com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction

interface CreateTransactionInput {
  fun create(transaction: Transaction): Transaction
}