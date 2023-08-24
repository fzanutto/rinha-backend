package com.fzanutto.rinhabackend.controller

import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException

@RestControllerAdvice
class ValidationControllerException {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BindException::class)
    fun handleValidationException(ex: BindException): ResponseEntity<List<String>> {
        val bindingResult = ex.bindingResult
        val errorMessages = bindingResult.fieldErrors.map {
            "${it.field}: ${it.defaultMessage}"
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessages)
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(SQLException::class)
    fun handlePSQLError(ex: SQLException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.message ?: "")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception::class)
    fun handleOtherError(ex: Exception): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.stackTraceToString())
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DuplicateKeyException::class)
    fun handleOtherError(ex: DuplicateKeyException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body("Apelido duplicado")
    }
}