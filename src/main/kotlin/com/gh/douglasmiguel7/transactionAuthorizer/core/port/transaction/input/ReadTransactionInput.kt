package com.gh.douglasmiguel7.transactionAuthorizer.core.port.transaction.input

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction

interface ReadTransactionInput {
  fun read(): List<Transaction>
}