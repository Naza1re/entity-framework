package com.kotlin.aop.dto.entity.search.request

data class SearchRequest(
    val query: String,
    val page: Int,
    val pageSize: Int,
    val sort: String,
) {
}