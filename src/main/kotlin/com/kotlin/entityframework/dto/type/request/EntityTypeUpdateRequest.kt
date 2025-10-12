package com.kotlin.entityframework.dto.type.request

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest

data class EntityTypeUpdateRequest(
    val description: String,
    val customFieldsToDelete: List<String>,
    val newCustomFields: List<CustomFieldRequest>
)