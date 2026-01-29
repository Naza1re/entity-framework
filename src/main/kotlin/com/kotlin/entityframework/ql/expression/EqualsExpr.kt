package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

data class EqualsExpr(val field: String,val value: String) : QlExpression {
    override fun <T> toSpecification(): Specification<T> =
        Specification { root, _, cb ->
            val path = root.get<Any>(field)
            val stringPath = cb.function("text", String::class.java, path)
            cb.equal(stringPath, value)
        }
}