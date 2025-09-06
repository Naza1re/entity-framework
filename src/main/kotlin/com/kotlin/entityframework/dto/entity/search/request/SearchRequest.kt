package com.kotlin.entityframework.dto.entity.search.request

class SearchRequest (
    val keyword: String,
    val page: Int,
    val pageSize: Int,
    val sort: String,
)