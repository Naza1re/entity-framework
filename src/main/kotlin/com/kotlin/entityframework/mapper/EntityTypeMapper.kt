package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.model.type.EntityType
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class EntityTypeMapper {
     abstract fun toEntityTypeResponse(entityType: EntityType): EntityTypeResponse
     abstract fun toEntityTypeResponseList(entities: List<EntityType>): List<EntityTypeResponse>

}