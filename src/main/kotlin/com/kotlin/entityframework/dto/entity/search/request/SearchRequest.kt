package com.kotlin.entityframework.dto.entity.search.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchRequest @JsonCreator constructor(
    @JsonProperty("keyword") val keyword: String = "",
    @JsonProperty("page") val page: Int = 0,
    @JsonProperty("pageSize") val pageSize: Int = 20,
    @JsonProperty("sort") val sort: String = "ASC"
)
