package com.kotlin.entityframework.dto.entity.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateRequest @JsonCreator constructor(
    @JsonProperty("entityTypeCode") val entityTypeCode: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("params") val params: Map<String, Any>
)
