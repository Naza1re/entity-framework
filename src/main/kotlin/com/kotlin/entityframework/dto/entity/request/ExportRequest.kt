package com.kotlin.entityframework.dto.entity.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ExportRequest @JsonCreator constructor(
    @JsonProperty("query") val query: String = "",
    @JsonProperty("page") val page: Int = 0,
    @JsonProperty("pageSize") val pageSize: Int = 20
)