package com.kotlin.entityframework.service.custom.field

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.exception.CustomFieldNotFoundException
import com.kotlin.entityframework.model.custom.field.CustomField
import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
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
    override fun getCustomFieldByName(name : String) : CustomField {
        return repository.findByName(name) ?: throw CustomFieldNotFoundException("Custom field with name $name not found")
    }

    @Transactional(readOnly = true)
    override fun getCustomFieldByNameAndEntityTypeCode(name : String, entityTypeCode : String) : CustomField {
        return repository.findByNameAndEntityTypeCode(name, entityTypeCode) ?: throw CustomFieldNotFoundException("Custom field with name $name not found")
    }



}
