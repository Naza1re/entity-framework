package com.kotlin.entityframework.dto.type.request

import com.kotlin.entityframework.dto.custom.field.response.CustomFieldResponse

data class EntityTypeRequest(
    val code: String,
    val name: String,
    val description: String,
    val customFields: List<CustomFieldResponse>
)