package com.kotlin.entityframework.exception.handler

import com.kotlin.entityframework.exception.*
import com.kotlin.entityframework.exception.error.ApplicationExceptionObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"
    }

    @ExceptionHandler(EntityNotFoundException::class,
        EntityTypeNotFoundException::class,
        EntityTypeNotContainsSuchCustomFieldException::class,
        CustomFieldNotFoundException::class,
        MetadataNotFoundException::class)
    fun handleNotFound(notFoundException: RuntimeException) : ResponseEntity<ApplicationExceptionObject> {
        val message = notFoundException.message ?: UNKNOWN_ERROR
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(message, 404), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EntityTypeCodeNotAlloyedException::class,
        EntityTypeAlreadyExistException::class,
        NotAlloyedValueException::class,
        MissingRequiredCustomFieldException::class,
        OperationNotSupportedException::class)
    fun handleConflictException(conflictException: RuntimeException) : ResponseEntity<ApplicationExceptionObject> {
        val message = conflictException.message ?: UNKNOWN_ERROR
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(message, 409), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(QlParseException::class)
    fun handleParseException(qlParseException: QlParseException) : ResponseEntity<ApplicationExceptionObject> {
        val message = qlParseException.message ?: UNKNOWN_ERROR
        return ResponseEntity<ApplicationExceptionObject>(
            ApplicationExceptionObject(message, 400), HttpStatus.BAD_REQUEST
        )
    }
}