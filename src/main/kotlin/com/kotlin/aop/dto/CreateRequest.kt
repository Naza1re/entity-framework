package com.kotlin.aop.dto

data class CreateRequest(

    val name: String,
    val params: Map<String, Any>
) {
}