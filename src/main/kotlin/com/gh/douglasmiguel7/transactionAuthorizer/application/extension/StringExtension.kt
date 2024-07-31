package com.gh.douglasmiguel7.transactionAuthorizer.application.extension

import java.util.UUID

fun String.toUUID(): UUID {
  return UUID.fromString(this)
}