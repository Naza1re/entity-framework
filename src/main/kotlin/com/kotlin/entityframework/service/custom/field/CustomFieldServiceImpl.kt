package com.kotlin.entityframework.service.custom.field

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.exception.CustomFieldNotFoundException
import com.kotlin.entityframework.exception.MetadataNotFoundException
import com.kotlin.entityframework.model.custom.field.CustomField
import com.kotlin.entityframework.model.custom.field.CustomFieldType
import com.kotlin.entityframework.repository.custom.field.CustomFieldRepository
import com.kotlin.entityframework.service.CustomFieldService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomFieldServiceImpl(
    private val repository: CustomFieldRepository
) : CustomFieldService {
    override fun createCustomFieldsToEntityType(customFieldsRequest: CustomFieldRequest): List<CustomField> {
        TODO("Not yet implemented")

    }

    @Transactional
    override fun deleteCustomFields(customFieldNames: List<String>) {
        repository.deleteByNameIn(customFieldNames)
    }

    @Transactional(readOnly = true)
    override fun getCustomFieldsByNames(customFieldNames: List<String>): List<CustomField> {
        return repository.findByNameIn(customFieldNames)
    }

    @Transactional(readOnly = true)
    override fun getCustomFieldByName(name : String) : MutableList<CustomField> {
        return repository.findByName(name)
    }

    @Transactional(readOnly = true)
    override fun getCustomFieldByNameAndEntityTypeCode(name : String, entityTypeCode : String) : CustomField {
        return repository.findByNameAndEntityTypeCode(name, entityTypeCode) ?: throw CustomFieldNotFoundException("Custom field with name $name not found")
    }

    override fun getCustomFieldTypeByCode(code: String): CustomFieldType {
        val customField = repository.findByCode(code)
            ?: throw CustomFieldNotFoundException("Custom field with code $code not found")

        return customField.metadata?.type
            ?: throw MetadataNotFoundException("Metadata or type is missing for custom field with code $code")
    }

}
