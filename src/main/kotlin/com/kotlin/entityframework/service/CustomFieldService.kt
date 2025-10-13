package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.model.custom.field.CustomField

interface CustomFieldService {

    fun createCustomFieldsToEntityType(customFieldsRequest: CustomFieldRequest) : List<CustomField>
    fun deleteCustomFields(customFieldCodes: List<String>)
    fun getCustomFieldsByCodes(customFieldCodes: List<String>) : List<CustomField>
}