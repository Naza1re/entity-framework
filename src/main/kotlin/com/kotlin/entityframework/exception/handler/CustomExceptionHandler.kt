package com.kotlin.entityframework.exception.handler

import com.kotlin.entityframework.exception.EntityNotFoundException
import com.kotlin.entityframework.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.entityframework.exception.EntityTypeNotFoundException
import com.kotlin.entityframework.exception.error.ApplicationExceptionObject
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