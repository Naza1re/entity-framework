package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.ql.parser.QlParser
import com.kotlin.entityframework.service.CustomFieldService
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class SpecificationCreator(
    private val customFieldService: CustomFieldService
) {
    fun <T> entitySpecificationCreate(query: String): Specification<T> {
        val expr = QlParser.parse(query)
        return Specification { root, query, cb ->
            // Создаем новый экземпляр Visitor для каждого запроса
            val visitor = JpaSpecificationVisitor(root, query, cb, customFieldService)
            expr.accept(visitor)
        }
    }
}