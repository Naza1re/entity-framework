package com.kotlin.entityframework.dto.type.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EntityTypeRequestSearch @JsonCreator constructor(
    @JsonProperty("page") val page: Int,
    @JsonProperty("size") val size: Int,
    @JsonProperty("prefix") val prefix: String
)
