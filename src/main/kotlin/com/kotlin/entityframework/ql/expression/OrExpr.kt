package com.kotlin.entityframework.ql.expression

import com.kotlin.entityframework.repository.specification.QlVisitor

data class OrExpr(val left: QlExpression, val right: QlExpression) : QlExpression {

    override fun <R> accept(visitor: QlVisitor<R>): R = visitor.visit(this)
}