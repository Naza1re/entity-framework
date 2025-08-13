package com.kotlin.aop.exception.handler

import com.kotlin.aop.exception.EntityNotFoundException
import com.kotlin.aop.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.aop.exception.EntityTypeNotFoundException
import com.kotlin.aop.exception.error.ApplicationExceptionObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class,
        EntityTypeNotFoundException::class,
        EntityTypeNotContainsSuchCustomFieldException::class)
    fun handleNotFound(runtimeException: RuntimeException) : ResponseEntity<ApplicationExceptionObject> {
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(runtimeException.message!!, 404), HttpStatus.NOT_FOUND)
    }
}