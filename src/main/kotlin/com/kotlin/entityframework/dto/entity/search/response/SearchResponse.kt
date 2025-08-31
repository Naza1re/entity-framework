package com.kotlin.entityframework.dto.entity.search.response

import com.kotlin.entityframework.dto.entity.response.EntityResponse

data class SearchResponse(
    val entityList: List<EntityResponse>
) {
}