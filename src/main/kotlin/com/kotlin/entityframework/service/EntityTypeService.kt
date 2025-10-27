package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.type.request.EntityTypeCreateRequest
import com.kotlin.entityframework.dto.type.request.EntityTypeRequestSearch
import com.kotlin.entityframework.dto.type.request.EntityTypeUpdateRequest
import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.model.type.EntityType

interface EntityTypeService  {
    fun getEntityTypeByCode(code:String) : EntityType
    fun getEntityType(code: String): EntityTypeResponse
    fun createEntityType(request: EntityTypeCreateRequest): EntityTypeResponse?
    fun getEntityTypesByRequest(searchRequest: EntityTypeRequestSearch): List<EntityTypeResponse>
    fun updateEntityType(entityTypeCode: String, updateRequest: EntityTypeUpdateRequest): EntityTypeResponse
    fun deleteEntityType(entityTypeCode: String)
}