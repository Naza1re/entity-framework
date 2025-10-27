package com.kotlin.entityframework.exception.handler

import com.kotlin.entityframework.exception.*
import com.kotlin.entityframework.exception.error.ApplicationExceptionObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class,
        EntityTypeNotFoundException::class,
        EntityTypeNotContainsSuchCustomFieldException::class,
        CustomFieldNotFoundException::class)
    fun handleNotFound(notFoundException: RuntimeException) : ResponseEntity<ApplicationExceptionObject> {
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(notFoundException.message!!, 404), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EntityTypeCodeNotAlloyedException::class,
        EntityTypeAlreadyExistException::class,
        NotAlloyedValueException::class,
        MissingRequiredCustomFieldException::class)
    fun handleConflictException(conflictException: RuntimeException) : ResponseEntity<ApplicationExceptionObject> {
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(conflictException.message!!, 409), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(QlParseException::class)
    fun handleParseException(qlParseException: QlParseException) : ResponseEntity<ApplicationExceptionObject> {
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(qlParseException.message!!, 400), HttpStatus.BAD_REQUEST
        )
    }
}