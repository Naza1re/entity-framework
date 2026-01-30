package com.kotlin.entityframework.ql.expression

import com.kotlin.entityframework.repository.specification.QlVisitor

data class LikeExpr(val field: String,val value: String) : QlExpression {
    override fun <R> accept(visitor: QlVisitor<R>): R = visitor.visit(this)
}