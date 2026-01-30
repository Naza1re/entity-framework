package com.kotlin.entityframework.ql.expression

import com.kotlin.entityframework.repository.specification.QlVisitor

interface QlExpression {
    fun <R> accept(visitor: QlVisitor<R>): R
}