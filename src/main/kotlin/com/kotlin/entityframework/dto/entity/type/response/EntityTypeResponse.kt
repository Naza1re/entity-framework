package com.kotlin.entityframework.dto.entity.type.response

import com.kotlin.entityframework.dto.entity.custom.field.response.CustomFieldResponse

data class EntityTypeResponse(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val customFields: List<CustomFieldResponse>,
) {
}