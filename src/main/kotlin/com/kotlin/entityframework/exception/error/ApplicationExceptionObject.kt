package com.kotlin.entityframework.exception.error

data class ApplicationExceptionObject(
    val message: String,
    val statusCode: Int
) {
}