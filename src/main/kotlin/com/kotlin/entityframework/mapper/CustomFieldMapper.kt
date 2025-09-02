package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.custom.field.value.CustomFieldValueResponse
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
}
