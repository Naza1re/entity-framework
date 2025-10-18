package com.kotlin.entityframework.dto.entity.search.request

data class QlSearchRequest(
    val query: String,
    val page: Int,
    val pageSize: Int,
)