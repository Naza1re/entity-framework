package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.custom.field.response.CustomFieldResponse
import com.kotlin.entityframework.dto.custom.field.value.CustomFieldValueResponse
import com.kotlin.entityframework.model.custom.field.CustomField
import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
import org.mapstruct.Mapper
import org.mapstruct.Named

@Mapper(componentModel = "spring")
abstract class CustomFieldMapper {

    @Named("toCustomFieldValue")
    fun toCustomFieldValue(properties: Map<String, Any>) : List<CustomFieldValueResponse> {
        val list = mutableListOf<CustomFieldValueResponse>()

        for ((key, value) in properties) {
            if (value is String) {
                list.add(CustomFieldValueResponse(key, value))
            }
        }
        return list
    }

    @Named("toCustomFieldResponse")
    fun toCustomFieldResponse(customFields: MutableSet<CustomFieldEntityType>) : List<CustomFieldResponse> {

        val list = mutableListOf<CustomFieldResponse>()

        for (customField in customFields) {
            val customFieldResponse = CustomFieldResponse(
                customField.id,
                customField.customField.code,
                customField.customField.metadata!!.type)
            list.add(customFieldResponse)
        }
        return list
    }
}

