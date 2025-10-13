package com.kotlin.entityframework.exception

class EntityTypeNotFoundException(private val msg: String) : RuntimeException(msg) {
}