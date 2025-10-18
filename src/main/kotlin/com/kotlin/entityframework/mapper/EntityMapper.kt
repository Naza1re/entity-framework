package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.model.entity.MyEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [CustomFieldMapper::class])
interface EntityMapper {

    @Mapping(source = "entity.entityType.code", target = "entityTypeCode")
    @Mapping(source = "properties", target = "customFieldsValue", qualifiedByName = ["toCustomFieldValue"])
    fun toEntityResponse(entity: MyEntity?): EntityResponse?

    fun toEntityList(entities: MutableList<MyEntity>?): MutableList<EntityResponse>

    fun toEntityListAfterQlSearch(model: MutableList<MyEntity>): MutableList<EntityResponse>

}