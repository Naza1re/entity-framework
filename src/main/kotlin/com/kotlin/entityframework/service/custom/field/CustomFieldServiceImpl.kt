package com.kotlin.entityframework.service.custom.field

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.model.custom.field.CustomField
import com.kotlin.entityframework.repository.custom.field.CustomFieldRepository
import com.kotlin.entityframework.service.CustomFieldService
import org.springframework.stereotype.Service

@Service
class CustomFieldServiceImpl(
    private val repository: CustomFieldRepository
) : CustomFieldService {
    override fun createCustomFieldsToEntityType(customFieldsRequest: CustomFieldRequest): List<CustomField> {
        TODO("Not yet implemented")
    }

    override fun deleteCustomFields(customFieldCodes: List<String>) {
        repository.deleteByCodeIn(customFieldCodes)
    }

    override fun getCustomFieldsByCodes(customFieldCodes: List<String>): List<CustomField> {
        return repository.findByCodeIn(customFieldCodes)
    }


}