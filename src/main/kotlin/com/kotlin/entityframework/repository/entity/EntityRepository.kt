package com.kotlin.entityframework.repository.entity

import com.kotlin.entityframework.model.entity.Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository : JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {

    fun findByNumber(number: String): Entity?
}