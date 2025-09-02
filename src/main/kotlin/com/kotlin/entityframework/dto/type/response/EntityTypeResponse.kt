package com.kotlin.entityframework.dto.type.response

import com.kotlin.entityframework.dto.custom.field.response.CustomFieldResponse

data class EntityTypeResponse(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val customFields: List<com.kotlin.entityframework.dto.custom.field.response.CustomFieldResponse>,
) {
}