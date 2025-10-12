package com.kotlin.entityframework.repository.type

import com.kotlin.entityframework.model.type.EntityType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EntityTypeRepository : JpaRepository<EntityType, Long>, JpaSpecificationExecutor<EntityType> {
    fun findByCode(code: String): EntityType?

    @Query("" +
            "select ")
    fun findEntityTypePage()
}