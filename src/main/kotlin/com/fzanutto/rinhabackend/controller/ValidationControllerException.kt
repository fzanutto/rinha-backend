package com.fzanutto.rinhabackend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

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
}