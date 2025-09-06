package com.kotlin.entityframework.dto.entity.request

data class UpdateRequest(
    val name: String,
    val number: String,
    val params: Map<String, Any>
) {
}