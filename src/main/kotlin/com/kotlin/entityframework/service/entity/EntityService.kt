package com.kotlin.entityframework.service.entity

import com.kotlin.entityframework.dto.entity.request.CreateRequest
import com.kotlin.entityframework.dto.entity.request.UpdateRequest
import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.dto.entity.search.request.SearchRequest
import com.kotlin.entityframework.exception.EntityNotFoundException
import com.kotlin.entityframework.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.entityframework.mapper.EntityMapper
import com.kotlin.entityframework.model.entity.EntityType
import com.kotlin.entityframework.model.entity.MyEntity
import com.kotlin.entityframework.repository.entity.EntityRepository
import com.kotlin.entityframework.repository.specification.MyEntitySpecifications
import com.kotlin.entityframework.service.EntitySearchService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EntityService(
    private val repository : EntityRepository,
    private val entityTypeService : EntityTypeService,
    private val entityMapper: EntityMapper,
) {
    fun getByNumber(number: String): EntityResponse? {
        return entityMapper.toEntityResponse(getEntityOrThrow(number))
    }

    fun search(searchRequest: SearchRequest): List<EntityResponse> {
        val pageRequest = PageRequest.of(searchRequest.page, searchRequest.pageSize)
        val specification = MyEntitySpecifications.byProperties(parseQuery(searchRequest.query))
        val entityList = repository.findAll(specification, pageRequest)
        return entityMapper.toEntityList(entityList.content)
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

    fun deleteByNumber(number: String) {
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
        return resultParams
    }

    fun updateEntity(number: String, updateRequest: UpdateRequest): EntityResponse? {
        val entity = getEntityOrThrow(number)
        val entityType = entityTypeService.getEntityTypeByCode(entity.entityType.code)
        if (entityType != null) {
            validateCustomFields(updateRequest.params, entityType)
        }

        val entityToSave = MyEntity(
            id = entity.id,
            name = updateRequest.name,
            number = UUID.randomUUID().toString(),
            properties = putElementsToEntity(updateRequest.params),
            entityType = entityType!!
        )
        val updatedEntity = repository.save(entityToSave)
        return entityMapper.toEntityResponse(updatedEntity)
    }

    fun parseQuery(query: String): Map<String, Any> {
        return query.split("and")
            .map { it.trim() }
            .map { condition ->
                val parts = condition.split("=")
                val key = parts[0].trim()
                val value = parts[1].trim().removeSurrounding("'")
                key to value
            }.toMap()
    }

}
