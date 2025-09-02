package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.model.type.EntityType
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class EntityTypeMapper {
     abstract fun toEntityTypeResponse(entityType: EntityType): com.kotlin.entityframework.dto.type.response.EntityTypeResponse
}