package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification
import java.util.UUID

data class EqualsExpr(val field: String,val value: String) : QlExpression {
    override fun <T> toSpecification(): Specification<T> =
        Specification { root, query, cb ->

            val entityTypeJoin = root.join<Any, Any>("entityType")
            val customFieldEntityTypeJoin = entityTypeJoin.join<Any, Any>("customFields")
            val customFieldJoin = customFieldEntityTypeJoin.join<Any, Any>("customField")
            val fieldCodePath = customFieldJoin.get<String>("code")
            val fieldNamePath = customFieldJoin.get<String>("name")

            val hasFieldInType = cb.equal(fieldCodePath, field)
            val jsonExtract = cb.function(
                "jsonb_extract_path_text",
                String::class.java,
                root.get<String>("properties"),
                fieldNamePath // Ключ в JSON берется динамически из метаданных
            )

            val valueMatches = cb.equal(jsonExtract, value)
            query?.distinct(true)
            cb.and(hasFieldInType, valueMatches)
        }
}