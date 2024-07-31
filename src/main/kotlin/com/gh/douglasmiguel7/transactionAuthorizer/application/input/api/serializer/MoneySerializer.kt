package com.gh.douglasmiguel7.transactionAuthorizer.account.application.input.api.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

class MoneySerializer() : JsonSerializer<BigDecimal>() {
  override fun serialize(value: BigDecimal, generator: JsonGenerator, provider: SerializerProvider) {
    generator.writeString(value.setScale(2, HALF_UP).toString());
  }
}