package com.kotlin.entityframework.mapper

import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.model.type.EntityType
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [CustomFieldMapper::class])
abstract class EntityTypeMapper {
     @Mapping(target = "customFields", source = "customFields", qualifiedByName = ["toCustomFieldResponse"])
     abstract fun toEntityTypeResponse(entityType: EntityType): EntityTypeResponse
     abstract fun toEntityTypeResponseList(entities: List<EntityType>): List<EntityTypeResponse>

}