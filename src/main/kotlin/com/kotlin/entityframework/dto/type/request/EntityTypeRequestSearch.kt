package com.kotlin.entityframework.dto.type.request

data class EntityTypeRequestSearch(
    val page: Int,
    val size: Int,
    val prefix: String
)
