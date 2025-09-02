package com.kotlin.entityframework.service.type

import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.exception.EntityTypeNotFoundException
import com.kotlin.entityframework.mapper.EntityTypeMapper
import com.kotlin.entityframework.model.type.EntityType
import com.kotlin.entityframework.repository.type.EntityTypeRepository
import com.kotlin.entityframework.service.EntityTypeService
import org.springframework.stereotype.Service

@Service
class EntityTypeServiceImpl(
    private val repository : EntityTypeRepository,
    private val mapper: EntityTypeMapper
) : EntityTypeService {

    override fun getEntityTypeByCode(code:String) : EntityType? {
        return repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
    }

    override fun getEntityType(code: String): EntityTypeResponse? {
        val entityType = repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
        return mapper.toEntityTypeResponse(entityType)
    }
}