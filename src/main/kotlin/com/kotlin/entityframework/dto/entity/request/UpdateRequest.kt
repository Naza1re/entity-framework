package com.kotlin.entityframework.dto.entity.request

data class UpdateRequest(
    val name: String,
    val params: Map<String, Any>
)