package com.kotlin.entityframework.dto.type.request

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EntityTypeCreateRequest @JsonCreator constructor(
    @JsonProperty("code") val code: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("customFields") val customFields: List<CustomFieldRequest>
)
