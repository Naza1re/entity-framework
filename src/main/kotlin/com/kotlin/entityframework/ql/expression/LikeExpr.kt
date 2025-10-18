package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

data class LikeExpr(val field: String, val value: String) : QlExpression {
    override fun <T> toSpecification(): Specification<T> {
        return Specification {root, _, criteriaBuilder ->
            criteriaBuilder.like(root.get(field), "%$value%")
        }
    }
}