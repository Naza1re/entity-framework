package com.kotlin.entityframework.dto.entity.response

import com.kotlin.entityframework.dto.entity.custom.field.value.CustomFieldValueResponse

data class EntityResponse(
    val id: Long,
    val entityTypeCode: String,
    val name: String,
    val number: String,
    val customFieldsValue: List<CustomFieldValueResponse>
)