package com.kotlin.entityframework.dto.type.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest

data class EntityTypeUpdateRequest @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("customFieldsToDelete") val customFieldsToDelete: List<String>? = emptyList(),
    @JsonProperty("newCustomFields") val newCustomFields: List<CustomFieldRequest>? = emptyList(),
)
