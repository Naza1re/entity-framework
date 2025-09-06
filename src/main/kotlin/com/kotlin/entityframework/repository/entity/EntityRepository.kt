package com.kotlin.entityframework.repository.entity

import com.kotlin.entityframework.model.entity.MyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository : JpaRepository<MyEntity, Long>, JpaSpecificationExecutor<MyEntity> {

    fun findByNumber(number: String): MyEntity?
}