package com.kotlin.aop.dto.entity.search.response

import com.kotlin.aop.dto.entity.response.EntityResponse

data class SearchResponse(
    val entityList: List<EntityResponse>
) {
}