package com.kotlin.aop.service.entity

import com.kotlin.aop.exception.EntityTypeNotFoundException
import com.kotlin.aop.model.entity.EntityType
import com.kotlin.aop.repository.entity.EntityTypeRepository
import org.springframework.stereotype.Service

@Service
class EntityTypeService(
    private val repository : EntityTypeRepository
) {

    fun getEntityTypeByCode(code:String) : EntityType? {
        return repository.findByCode(code)
            ?: throw EntityTypeNotFoundException("Entity type by code : $code not found")
    }
}