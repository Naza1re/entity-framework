package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

data class LikeExpr(val field: String,val value: String) : QlExpression {
    override fun <T> toSpecification(): Specification<T> =
        Specification { root, query, cb ->
            val isUuid = field.matches(Regex("""\p{XDigit}{8}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{12}"""))
            if (isUuid) {
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
                    fieldNamePath
                )
                query?.distinct(true)
                cb.and(hasFieldInType, cb.like(jsonExtract, "%$value%"))
            } else {
                val jsonExtract = cb.function(
                    "jsonb_extract_path_text",
                    String::class.java,
                    root.get<String>("properties"),
                    cb.literal(field)
                )
                cb.like(jsonExtract, "%$value%")
            }
        }
}