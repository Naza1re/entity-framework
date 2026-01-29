package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.custom.field.request.CustomFieldRequest
import com.kotlin.entityframework.model.custom.field.CustomField

interface CustomFieldService {

    fun createCustomFieldsToEntityType(customFieldsRequest: CustomFieldRequest) : List<CustomField>
    fun deleteCustomFields(customFieldNames: List<String>)
    fun getCustomFieldsByNames(customFieldNames: List<String>) : List<CustomField>
    fun getCustomFieldByName(name: String) : CustomField
    fun getCustomFieldByNameAndEntityTypeCode(name : String, entityTypeCode : String) : CustomField
}