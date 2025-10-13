package com.kotlin.entityframework.dto.type.request

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest

data class EntityTypeCreateRequest(
    val code: String,
    val name: String,
    val description: String,
    val customFields: List<CustomFieldRequest>
)