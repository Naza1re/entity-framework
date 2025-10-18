package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

data class AndExpr(val left: QlExpression, val right: QlExpression) : QlExpression {
    override fun <T> toSpecification(): Specification<T> {
        val leftSpec = left.toSpecification<T>()
        val rightSpec = right.toSpecification<T>()
        return Specification { root, query, cb ->
            cb.and(
                leftSpec.toPredicate(root, query, cb),
                rightSpec.toPredicate(root, query, cb)
            )
        }
    }
}