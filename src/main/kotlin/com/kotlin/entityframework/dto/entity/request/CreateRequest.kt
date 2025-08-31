package com.kotlin.entityframework.dto.entity.request

data class CreateRequest(
    val entityTypeCode: String,
    val name: String,
    val params: Map<String, Any>
) {
}