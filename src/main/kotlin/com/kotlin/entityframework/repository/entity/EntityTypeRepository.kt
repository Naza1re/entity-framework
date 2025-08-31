package com.kotlin.entityframework.repository.entity

import com.kotlin.entityframework.model.entity.EntityType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EntityTypeRepository : JpaRepository<EntityType, Long> {
    fun findByCode(code: String): EntityType?
}