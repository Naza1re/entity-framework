package com.kotlin.entityframework.dto.custom.field.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.entityframework.model.custom.field.CustomFieldType

data class CustomFieldResponse @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("code") val code: String,
    @JsonProperty("type") val type: CustomFieldType
)
