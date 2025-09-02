package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.model.entity.MyEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [CustomFieldMapper::class])
interface EntityMapper {

    @Mapping(source = "properties", target = "customValue", qualifiedByName = ["toCustomFieldValue"])
    fun toEntityResponse(dto: MyEntity?): EntityResponse?

    fun toEntityList(entities: MutableList<MyEntity>?): MutableList<EntityResponse>

    fun toEntity(model: MyEntity?): MyEntity?

}