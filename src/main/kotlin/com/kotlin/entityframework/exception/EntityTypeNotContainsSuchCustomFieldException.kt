package com.kotlin.entityframework.exception

class EntityTypeNotContainsSuchCustomFieldException(private val msg: String) : RuntimeException(msg) {
}