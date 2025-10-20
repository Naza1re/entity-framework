package com.kotlin.entityframework.dto.entity.search.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class QlSearchRequest @JsonCreator constructor(
    @JsonProperty("query") val query: String,
    @JsonProperty("page") val page: Int,
    @JsonProperty("pageSize") val pageSize: Int
)
