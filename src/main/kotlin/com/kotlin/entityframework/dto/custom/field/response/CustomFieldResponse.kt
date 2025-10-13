package com.kotlin.entityframework.dto.custom.field.response

import com.kotlin.entityframework.model.custom.field.CustomFieldType

data class CustomFieldResponse(
    val id: Long,
    val code: String,
    val type: CustomFieldType,
)
