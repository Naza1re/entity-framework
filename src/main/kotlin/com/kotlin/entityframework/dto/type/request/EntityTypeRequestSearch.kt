package com.kotlin.entityframework.dto.type.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EntityTypeRequestSearch @JsonCreator constructor(
    @JsonProperty("page") val page: Int = 0,
    @JsonProperty("size") val size: Int = 20,
    @JsonProperty("prefix") val prefix: String = ""
)
