package com.kotlin.aop.service.entity

import com.kotlin.aop.dto.entity.request.CreateRequest
import com.kotlin.aop.dto.entity.request.UpdateRequest
import com.kotlin.aop.dto.entity.response.EntityResponse
import com.kotlin.aop.dto.entity.search.request.SearchRequest
import com.kotlin.aop.exception.EntityNotFoundException
import com.kotlin.aop.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.aop.mapper.EntityMapper
import com.kotlin.aop.model.custom.field.CustomField
import com.kotlin.aop.model.entity.EntityType
import com.kotlin.aop.model.entity.MyEntity
import com.kotlin.aop.repository.entity.EntityRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EntityService(
    private val repository : EntityRepository,
    private val entityTypeService : EntityTypeService,
    private val entityMapper: EntityMapper
) {
    fun getByNumber(number: String): EntityResponse? {
        return entityMapper.toEntityResponse(getEntityOrThrow(number))
    }

    fun search(searchRequest: SearchRequest): List<MyEntity> {
        return emptyList()
    }

    fun createEntity(createRequest: CreateRequest): EntityResponse {
        val entityTypeCode = createRequest.entityTypeCode
        val entityType = entityTypeService.getEntityTypeByCode(entityTypeCode)
        if (entityType != null) {
            validateCustomFields(createRequest.params, entityType)
        }
        val entityToSave = MyEntity(
            id = 0,
            name = createRequest.name,
            number = UUID.randomUUID().toString(),
            properties = putElementsToEntity(createRequest.params),
            entityType = entityType!!
        )
        val savedEntity = repository.save(entityToSave)
        return entityMapper.toEntityResponse(savedEntity)!!
    }

    fun deleteByNumber(number: String) : Void {
        val entity = getEntityOrThrow(number)
        repository.delete(entity)
    }

    private fun getEntityOrThrow(number: String ): MyEntity {
        return repository.findByNumber(number)
                ?: throw EntityNotFoundException(number)
    }

    fun validateCustomFields(params: Map<String, Any>, entityType: EntityType) {
        val listOfEntityTypeFields = entityType.customFields.map {
             customField -> customField.customField.code
        }
        for ((key, value ) in params) {
            if (!listOfEntityTypeFields.contains(key)) {
                throw EntityTypeNotContainsSuchCustomFieldException("Field with code $key not allowed for this entityType")
            }
        }
        TODO("Validation")
    }

    fun putElementsToEntity(params: Map<String, Any>) : Map<String, Any> {
        val resultParams = HashMap<String, Any>()
        for ((key, value) in params) {
            resultParams[key] = value
        }
        TODO("Set type of customField")
        return resultParams
    }

    fun updateEntity(number: String, updateRequest: UpdateRequest): EntityResponse? {
        val entity = getEntityOrThrow(number)

    }
}