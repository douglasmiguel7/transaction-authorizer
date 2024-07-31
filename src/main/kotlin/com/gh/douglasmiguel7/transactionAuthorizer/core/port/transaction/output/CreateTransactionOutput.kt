package com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.output

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction

interface CreateTransactionOutput {
  fun create(transaction: Transaction): Transaction
}