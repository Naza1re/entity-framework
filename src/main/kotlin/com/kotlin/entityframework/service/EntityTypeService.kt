package com.kotlin.entityframework.service

import com.kotlin.entityframework.model.type.EntityType

interface EntityTypeService  {
    fun getEntityTypeByCode(code:String) : EntityType?
    fun getEntityType(code: String): com.kotlin.entityframework.dto.type.response.EntityTypeResponse?
}