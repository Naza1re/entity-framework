package com.kotlin.entityframework.exception

class EntityTypeAlreadyExistException(private val msg: String) : RuntimeException(msg) {
}