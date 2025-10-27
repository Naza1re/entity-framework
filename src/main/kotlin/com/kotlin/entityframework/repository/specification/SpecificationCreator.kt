package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.model.entity.Entity
import com.kotlin.entityframework.ql.QlToFilters
import com.kotlin.entityframework.ql.parser.QlParser
import org.springframework.data.jpa.domain.Specification

object SpecificationCreator {

    fun entitySpecificationCreate(query: String): Specification<Entity> {
        val expression = QlParser.parse(query)
        val filters = QlToFilters.toMap(expression)
        return EntityPropertiesSpecifications.byProperties(filters)
    }
}