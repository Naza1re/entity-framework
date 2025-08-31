package com.kotlin.entityframework.dto.entity.type.request

import com.kotlin.entityframework.dto.entity.custom.field.response.CustomFieldResponse

data class EntityTypeRequest(
    val code: String,
    val name: String,
    val description: String,
    val customFields: List<CustomFieldResponse>,
) {
}