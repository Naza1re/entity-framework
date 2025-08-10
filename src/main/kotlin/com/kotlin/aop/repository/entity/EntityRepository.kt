package com.kotlin.aop.repository.entity

import com.kotlin.aop.model.entity.MyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository : JpaRepository<MyEntity, Long>, JpaSpecificationExecutor<MyEntity> {

    fun findByNumber(number: String): MyEntity?
}