package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.validator

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository.AccountRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.UUID

class AccountExistsValidator(
  private val repository: AccountRepository,
) : ConstraintValidator<AccountExists, UUID> {

  lateinit var message: String

  override fun initialize(constraintAnnotation: AccountExists) {
    message = constraintAnnotation.message
  }

  override fun isValid(value: UUID?, context: ConstraintValidatorContext): Boolean {
    if (value == null) {
      return true
    }

    val exists = repository.existsById(value)

    if (!exists) {
      context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
    }

    return exists
  }
}