package com.kotlin.entityframework.repository.custom.field

import com.kotlin.entityframework.model.custom.field.CustomField
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CustomFieldRepository : JpaRepository<CustomField, Long>, JpaSpecificationExecutor<CustomField> {
    fun findByCode(code: String): CustomField?
    fun findByCodeIn(code: List<String>): MutableList<CustomField>
    fun deleteByCodeIn(code: List<String>)
}