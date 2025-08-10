package com.kotlin.aop.service.entity

import com.kotlin.aop.dto.CreateRequest
import com.kotlin.aop.dto.SearchRequest
import com.kotlin.aop.model.entity.MyEntity
import com.kotlin.aop.repository.entity.EntityRepository
import org.springframework.stereotype.Service

@Service
class EntityService(
    private val repository : EntityRepository
) {
    fun getByNumber(number: String): MyEntity {
        return repository.findByNumber(number)!!
    }

    fun search(searchRequest: SearchRequest): List<MyEntity> {
        return emptyList()
    }

    fun createEntity(createRequest: CreateRequest): MyEntity {
        TODO()
    }
}