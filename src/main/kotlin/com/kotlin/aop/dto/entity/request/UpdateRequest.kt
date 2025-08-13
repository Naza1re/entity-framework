package com.kotlin.aop.dto.entity.request

data class UpdateRequest(
    private val name: String,
    private val number: String,
    private val params: Map<String, Any>
) {
}