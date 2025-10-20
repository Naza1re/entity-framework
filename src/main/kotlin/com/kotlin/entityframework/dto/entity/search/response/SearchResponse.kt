package com.kotlin.entityframework.dto.entity.search.response

import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchResponse @JsonCreator constructor(
    @JsonProperty("entityList") val entityList: List<EntityResponse>,
    @JsonProperty("params") val params: Map<String, String>
)
