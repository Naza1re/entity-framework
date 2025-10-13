package com.kotlin.entityframework.exception

class EntityNotFoundException(private val msg: String) : RuntimeException(msg) {
}