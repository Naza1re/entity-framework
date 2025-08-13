package com.kotlin.aop.mapper

import com.kotlin.aop.dto.entity.response.EntityResponse
import com.kotlin.aop.model.entity.MyEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [])
interface EntityMapper {

    fun toEntityResponse(dto: MyEntity?): EntityResponse?

}