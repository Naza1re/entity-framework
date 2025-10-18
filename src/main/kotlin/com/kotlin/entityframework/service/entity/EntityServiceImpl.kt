package com.kotlin.entityframework.service.entity

import com.kotlin.entityframework.dto.entity.request.CreateRequest
import com.kotlin.entityframework.dto.entity.request.UpdateRequest
import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.dto.entity.search.request.QlSearchRequest
import com.kotlin.entityframework.dto.entity.search.request.SearchRequest
import com.kotlin.entityframework.exception.EntityNotFoundException
import com.kotlin.entityframework.exception.EntityTypeNotContainsSuchCustomFieldException
import com.kotlin.entityframework.exception.NotAlloyedValueException
import com.kotlin.entityframework.mapper.EntityMapper
import com.kotlin.entityframework.model.entity.Entity
import com.kotlin.entityframework.model.type.EntityType
import com.kotlin.entityframework.ql.QlToFilters
import com.kotlin.entityframework.ql.parser.QlParser
import com.kotlin.entityframework.repository.entity.EntityRepository
import com.kotlin.entityframework.repository.specification.EntityFieldConstants
import com.kotlin.entityframework.repository.specification.EntityFieldLikeSpecification
import com.kotlin.entityframework.repository.specification.EntityPropertiesSpecifications
import com.kotlin.entityframework.service.CustomFieldService
import com.kotlin.entityframework.service.EntityService
import com.kotlin.entityframework.service.type.EntityTypeServiceImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class EntityServiceImpl (
    private val repository : EntityRepository,
    private val entityTypeServiceImpl : EntityTypeServiceImpl,
    private val entityMapper: EntityMapper,
    private val customFieldService: CustomFieldService
) : EntityService {

    @Transactional(readOnly = true)
    override fun getByNumber(number: String): EntityResponse? {
        return entityMapper.toEntityResponse(getEntityOrThrow(number))
    }

    @Transactional(readOnly = true)
    override fun search(qlSearchRequest: QlSearchRequest): List<EntityResponse> {

        val pageRequest = PageRequest.of(qlSearchRequest.page, qlSearchRequest.pageSize)
        val expression = QlParser.parse(qlSearchRequest.query)
        val filters = QlToFilters.toMap(expression)
        val specification = EntityPropertiesSpecifications.byProperties(filters)

        val entityList = repository.findAll(specification, pageRequest)
        return entityMapper.toEntityListAfterQlSearch(entityList.content)
    }

    @Transactional
    override fun createEntity(createRequest: CreateRequest): EntityResponse {
        val entityTypeCode = createRequest.entityTypeCode
        val entityType = entityTypeServiceImpl.getEntityTypeByCode(entityTypeCode)
            validateCustomFields(createRequest.params, entityType)
        val entityToSave = Entity(
                id = 0,
                number = UUID.randomUUID().toString(),
                properties = putElementsToEntity(createRequest.params),
                entityType = entityType,
                name = createRequest.name,
                updatedAt = LocalDateTime.now(),
        )
        val savedEntity = repository.save(entityToSave)
        return entityMapper.toEntityResponse(savedEntity)

    }

    @Transactional
    override fun deleteByNumber(number: String) {
        val entity = getEntityOrThrow(number)
        repository.delete(entity)
    }

    private fun getEntityOrThrow(number: String ): Entity {
        return repository.findByNumber(number)
                ?: throw EntityNotFoundException(number)
    }

    private fun validateCustomFields(params: Map<String, Any>, entityType: EntityType) {
        val listOfEntityTypeFields = entityType.customFields
        val customFieldCodes = listOfEntityTypeFields.map {
                customField -> customField.customField.code
        }
        for (key in params.keys) {
            if (!customFieldCodes.contains(key)) {
                throw EntityTypeNotContainsSuchCustomFieldException("Field with code $key not allowed for this entityType")
            }
            val value = params[key]
            val customField = customFieldService.getCustomFieldByCode(key)
            val max = customField.max
            val min = customField.min
            when (value) {
                is String -> {
                    if (value.length > max || value.length < min) {
                        throw NotAlloyedValueException("Value $value cannot be more than $max or less than $min for customField with code : ${customField.code}") }
                }
                is Int -> {
                    if (value > max || value < min) {
                        throw NotAlloyedValueException("Value $value cannot be more than $max or less than $min for customField with code : ${customField.code}") }
                    }
                }
        }
    }

    private fun putElementsToEntity(params: Map<String, Any>) : Map<String, Any> {
        val resultParams = HashMap<String, Any>()
        for ((key, value) in params) {
            resultParams[key] = value
        }
        return resultParams
    }

    @Transactional
    override fun updateEntity(number: String, updateRequest: UpdateRequest): EntityResponse {
        val entity = getEntityOrThrow(number)

        val entityTypeByCode = entityTypeServiceImpl.getEntityTypeByCode(entity.entityType.code)

        validateCustomFields(updateRequest.params, entityTypeByCode)

        entity.apply {
            name = updateRequest.name
            properties = putElementsToEntity(updateRequest.params)
            entityType = entityTypeByCode
            updatedAt = LocalDateTime.now()
        }

        val updatedEntity = repository.save(entity)
        return entityMapper.toEntityResponse(updatedEntity)
    }

    @Transactional(readOnly = true)
    override fun getEntities(searchRequest: SearchRequest): List<EntityResponse> {
        val pageRequest = PageRequest.of(searchRequest.page, searchRequest.pageSize)
        val spec = EntityFieldLikeSpecification.entityFieldLike(EntityFieldConstants.ENTITY_NAME, searchRequest.keyword)
        val findEntityList = repository.findAll(spec, pageRequest)
        return entityMapper.toEntityList(findEntityList.content)
    }

}
