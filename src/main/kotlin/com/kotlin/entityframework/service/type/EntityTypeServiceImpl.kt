package com.kotlin.entityframework.service.type

import com.kotlin.entityframework.config.EntityTypeProperties
import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.dto.type.request.EntityTypeCreateRequest
import com.kotlin.entityframework.dto.type.request.EntityTypeRequestSearch
import com.kotlin.entityframework.dto.type.request.EntityTypeUpdateRequest
import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.exception.EntityTypeCodeNotAlloyedException
import com.kotlin.entityframework.exception.EntityTypeNotFoundException
import com.kotlin.entityframework.mapper.EntityTypeMapper
import com.kotlin.entityframework.model.custom.field.CustomField
import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
import com.kotlin.entityframework.model.custom.field.CustomFieldType
import com.kotlin.entityframework.model.custom.field.CustomFieldsMetadata
import com.kotlin.entityframework.model.type.EntityType
import com.kotlin.entityframework.repository.custom.field.CustomFieldRepository
import com.kotlin.entityframework.repository.type.EntityTypeRepository
import com.kotlin.entityframework.service.CustomFieldService
import com.kotlin.entityframework.service.EntityTypeService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EntityTypeServiceImpl(
    private val repository : EntityTypeRepository,
    private val mapper: EntityTypeMapper,
    private val entityTypeProperties: EntityTypeProperties,
    private val customFieldRepository : CustomFieldRepository,
    private val customFieldsService: CustomFieldService
) : EntityTypeService {

    override fun createEntityType(request: EntityTypeCreateRequest): EntityTypeResponse? {
        isValidEntityTypeCode(request.code)
        val entityType = EntityType(
            name = request.name,
            code = request.code,
            description = request.description,
        )
        entityType
            .customFields
            .addAll(createCustomFieldsEntityTypeLinks(request.customFields, entityType))
        val savedEntityType = repository.save(entityType)

        return mapper.toEntityTypeResponse(savedEntityType)
    }

    override fun getEntityTypesByRequest(searchRequest: EntityTypeRequestSearch): List<EntityTypeResponse> {
        val pageRequest = PageRequest.of(searchRequest.page, searchRequest.size)
        val entityTypes = repository.findAll(pageRequest)
        return mapper.toEntityTypeResponseList(entityTypes.content)
    }

    override fun updateEntityType(entityTypeCode: String, updateRequest: EntityTypeUpdateRequest): EntityTypeResponse {
        val entityType = getEntityTypeByCode(entityTypeCode)
        customFieldsService
            .deleteCustomFields(customFieldsService
                .getCustomFieldsByCodes(updateRequest
                    .customFieldsToDelete).map { it.code })

        entityType
            .customFields
            .addAll(createCustomFieldsEntityTypeLinks(updateRequest.newCustomFields, entityType))

        entityType.name = updateRequest.name
        entityType.description = updateRequest.description

        return mapper.toEntityTypeResponse(repository.save(entityType))

    }

    override fun deleteEntityType(entityTypeCode: String) {
        val entityType = getEntityTypeByCode(entityTypeCode)
        repository.delete(entityType)
    }

    override fun getEntityTypeByCode(code: String) : EntityType {
        return repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
    }

    override fun getEntityType(code: String) : EntityTypeResponse {
        val entityType = repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
        return mapper.toEntityTypeResponse(entityType)
    }

    private fun getNullableEntityType(code: String) : EntityType? {
        return repository.findByCode(code)
    }

    private fun isValidEntityTypeCode(entityTypeCode: String) {
        val entityType = getNullableEntityType(entityTypeCode)
        if (entityType != null || entityTypeProperties.excludedCodes.contains(entityTypeCode)) {
            throw  EntityTypeCodeNotAlloyedException("Entity type with code '$entityTypeCode' not alloyed")
        }
    }

    private fun createCustomFieldsEntityTypeLinks(customFieldsRequest: List<CustomFieldRequest>, entityType: EntityType)
            : List<CustomFieldEntityType> {
        return customFieldsRequest.map { cfr ->

            val customField = customFieldRepository.findByCode(cfr.code) ?: run {
                val newField = CustomField(
                    code = cfr.code,
                    min = 0,
                    max = 100
                )
                val metadata = CustomFieldsMetadata(
                    customField = newField,
                    type = CustomFieldType.valueOf(cfr.type.uppercase())
                )
                newField.metadata = metadata
                customFieldRepository.save(newField)
            }

            CustomFieldEntityType(
                id = 0,
                customField = customField,
                entityType = entityType
            )
        }
    }
}
