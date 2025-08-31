package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.entity.type.response.EntityTypeResponse
import com.kotlin.entityframework.model.entity.EntityType
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class EntityTypeMapper(
) {

     abstract fun toEntityTypeResponse(entityType: EntityType): EntityTypeResponse
}