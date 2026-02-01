package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.ql.expression.AndExpr
import com.kotlin.entityframework.ql.expression.EqualsExpr
import com.kotlin.entityframework.ql.expression.GreaterThanExpr
import com.kotlin.entityframework.ql.expression.LikeExpr
import com.kotlin.entityframework.ql.expression.OrExpr

interface QlVisitor<T> {
    fun visit(expr: EqualsExpr): T
    fun visit(expr: LikeExpr): T
    fun visit(expr: GreaterThanExpr): T
    fun visit(expr: OrExpr): T
    fun visit(expr: AndExpr): T
}