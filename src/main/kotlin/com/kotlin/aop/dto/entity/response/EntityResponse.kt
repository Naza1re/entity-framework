package com.kotlin.aop.dto.entity.response

data class EntityResponse(
    private val name: String,
    private val number: String,
    private val params: Map<String, Any>
) {
}