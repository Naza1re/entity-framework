package com.kotlin.aop.repository.entity

import com.kotlin.aop.model.entity.EntityType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EntityTypeRepository : JpaRepository<EntityType, Long> {
    fun findByCode(code: String): EntityType?
}