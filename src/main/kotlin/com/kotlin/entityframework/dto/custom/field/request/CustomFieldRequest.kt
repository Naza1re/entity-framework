package com.kotlin.entityframework.dto.custom.field.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CustomFieldRequest @JsonCreator constructor(
    @JsonProperty("code") val code: String = "",
    @JsonProperty("type") val type: String = ""
)
