package com.kotlin.entityframework.dto.custom.field.value

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CustomFieldValueResponse @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("value") val value: Any
)
