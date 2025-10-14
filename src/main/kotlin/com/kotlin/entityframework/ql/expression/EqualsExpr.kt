package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

data class EqualsExpr(val field: String, val value: String) : QlExpression {
    override fun <T> toSpecification(): Specification<T> {
        return Specification { root, _, cb ->
            cb.equal(root.get<String>(field), value)
        }
    }
}