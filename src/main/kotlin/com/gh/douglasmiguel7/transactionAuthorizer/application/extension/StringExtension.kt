package com.gh.douglasmiguel7.transactionAuthorizer.account.application.extension

import java.util.UUID

fun String.toUUID(): UUID {
  return UUID.fromString(this)
}