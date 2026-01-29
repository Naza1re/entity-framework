package com.kotlin.entityframework.repository.custom.field

import com.kotlin.entityframework.model.custom.field.CustomField
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomFieldRepository : JpaRepository<CustomField, Long>, JpaSpecificationExecutor<CustomField> {
    fun findByNameIn(name: List<String>): MutableList<CustomField>
    fun deleteByNameIn(name: List<String>)
    fun findByName(name: String): CustomField?
    @Query("""
        SELECT cf FROM CustomField cf
        JOIN cf.customFieldToEntityTypes cfe
        JOIN cfe.entityType et
        WHERE cf.name = :name 
          AND et.code = :entityTypeCode
    """)
    fun findByNameAndEntityTypeCode(
        @Param("name") name: String,
        @Param("entityTypeCode") entityTypeCode: String
    ): CustomField?
}
