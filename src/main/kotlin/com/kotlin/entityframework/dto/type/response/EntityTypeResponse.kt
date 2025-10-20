package com.kotlin.entityframework.dto.type.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.entityframework.dto.custom.field.response.CustomFieldResponse

data class EntityTypeResponse @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("code") val code: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("customFields") val customFields: List<CustomFieldResponse>
)
