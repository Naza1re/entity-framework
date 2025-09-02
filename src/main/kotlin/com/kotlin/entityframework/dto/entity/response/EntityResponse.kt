package com.kotlin.entityframework.dto.entity.response

import com.kotlin.entityframework.dto.custom.field.value.CustomFieldValueResponse

data class EntityResponse(
    val id: Long,
    val entityTypeCode: String,
    val name: String,
    val number: String,
    val customFieldsValue: List<com.kotlin.entityframework.dto.custom.field.value.CustomFieldValueResponse>
)