package com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.advice

import com.gh.douglasmiguel7.transactionAuthorizer.application.input.api.response.ValidationErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@ControllerAdvice
class ExceptionControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidation(exception: MethodArgumentNotValidException): ResponseEntity<List<ValidationErrorResponse>> {
    val response = exception.allErrors.map { error ->
      ValidationErrorResponse(
        property = error.codes!![0]!!.split(".").last(),
        cause = error.defaultMessage!!,
      )
    }

    return ResponseEntity(response, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(HandlerMethodValidationException::class)
  fun handleValidation(exception: HandlerMethodValidationException): ResponseEntity<List<ValidationErrorResponse>> {
    val response = exception.allErrors.map { error ->
      ValidationErrorResponse(
        property = error.codes!!.first()!!.split(".").last(),
        cause = error.defaultMessage!!,
      )
    }

    return ResponseEntity(response, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException::class)
  fun handleTypeMismatch(exception: MethodArgumentTypeMismatchException): ResponseEntity<ValidationErrorResponse> {
    val response = ValidationErrorResponse(
      property = exception.name,
      cause = "invalid format"
    )

    return ResponseEntity(response, HttpStatus.BAD_REQUEST)
  }
}