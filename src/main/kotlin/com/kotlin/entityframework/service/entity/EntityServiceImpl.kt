package com.kotlin.entityframework.service.entity

import com.kotlin.entityframework.dto.entity.request.CreateRequest
import com.kotlin.entityframework.dto.entity.request.UpdateRequest
import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.dto.entity.search.request.SearchRequest
import com.kotlin.entityframework.exception.EntityNotFoundException
import com.kotlin.entityframework.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.entityframework.mapper.EntityMapper
import com.kotlin.entityframework.model.type.EntityType
import com.kotlin.entityframework.model.entity.MyEntity
import com.kotlin.entityframework.repository.entity.EntityRepository
import com.kotlin.entityframework.repository.specification.EntityPropertiesSpecifications
import com.kotlin.entityframework.service.EntityService
import com.kotlin.entityframework.service.type.EntityTypeServiceImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class EntityServiceImpl (
    private val repository : EntityRepository,
    private val entityTypeServiceImpl : EntityTypeServiceImpl,
    private val entityMapper: EntityMapper,
) : EntityService {

    @Transactional(readOnly = true)
    override fun getByNumber(number: String): EntityResponse? {
        return entityMapper.toEntityResponse(getEntityOrThrow(number))
    }

    @Transactional(readOnly = true)
    override fun search(searchRequest: SearchRequest): List<EntityResponse> {
        val pageRequest = PageRequest.of(searchRequest.page, searchRequest.pageSize)
        val specification = EntityPropertiesSpecifications.byProperties(parseQuery(searchRequest.query))
        val entityList = repository.findAll(specification, pageRequest)
        return entityMapper.toEntityList(entityList.content)
    }

    @Transactional
    override fun createEntity(createRequest: CreateRequest): EntityResponse {
        val entityTypeCode = createRequest.entityTypeCode
        val entityType = entityTypeServiceImpl.getEntityTypeByCode(entityTypeCode)
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

    @Transactional
    override fun deleteByNumber(number: String) {
        val entity = getEntityOrThrow(number)
        repository.delete(entity)
    }

    private fun getEntityOrThrow(number: String ): MyEntity {
        return repository.findByNumber(number)
                ?: throw EntityNotFoundException(number)
    }

    private fun validateCustomFields(params: Map<String, Any>, entityType: EntityType) {
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

    private fun putElementsToEntity(params: Map<String, Any>) : Map<String, Any> {
        val resultParams = HashMap<String, Any>()
        for ((key, value) in params) {
            resultParams[key] = value
        }
        return resultParams
    }

    @Transactional
    override fun updateEntity(number: String, updateRequest: UpdateRequest): EntityResponse? {
        val entity = getEntityOrThrow(number)
        val entityType = entityTypeServiceImpl.getEntityTypeByCode(entity.entityType.code)
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
