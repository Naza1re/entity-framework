package com.kotlin.entityframework.exception.error

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ApplicationExceptionObject @JsonCreator constructor(
    @JsonProperty("message") val message: String,
    @JsonProperty("statusCode") val statusCode: Int
)