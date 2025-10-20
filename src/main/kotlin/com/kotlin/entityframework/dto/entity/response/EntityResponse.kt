package com.kotlin.entityframework.dto.entity.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.entityframework.dto.custom.field.value.CustomFieldValueResponse

data class EntityResponse @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("entityTypeCode") val entityTypeCode: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("number") val number: String,
    @JsonProperty("customFieldsValue") val customFieldsValue: List<CustomFieldValueResponse>
)
