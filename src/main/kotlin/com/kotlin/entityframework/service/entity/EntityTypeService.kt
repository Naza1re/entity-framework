package com.kotlin.entityframework.service.entity

import com.kotlin.entityframework.dto.entity.type.response.EntityTypeResponse
import com.kotlin.entityframework.exception.EntityTypeNotFoundException
import com.kotlin.entityframework.mapper.EntityTypeMapper
import com.kotlin.entityframework.model.entity.EntityType
import com.kotlin.entityframework.repository.entity.EntityTypeRepository
import org.springframework.stereotype.Service

@Service
class EntityTypeService(
    private val repository : EntityTypeRepository,
    private val mapper: EntityTypeMapper
) {


    fun getEntityTypeByCode(code:String) : EntityType? {
        return repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
    }

    fun getEntityType(code: String): EntityTypeResponse? {
        val entityType = repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
        return mapper.toEntityTypeResponse(entityType)
    }
}