package com.kotlin.entityframework.ql.expression

import org.springframework.data.jpa.domain.Specification

interface QlExpression {
    fun <T> toSpecification(): Specification<T>
}