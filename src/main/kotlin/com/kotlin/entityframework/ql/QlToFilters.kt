package com.kotlin.entityframework.ql

import com.kotlin.entityframework.ql.expression.*

/**
 * QlExpression → Map<String, Any>.
 */
object QlToFilters {

    private const val LIKE_MARK = "_like"

    fun toMap(expr: QlExpression): Map<String, Any> {
        val result = mutableMapOf<String, Any>()
        collect(expr, result)
        return result
    }

    private fun collect(expr: QlExpression, map: MutableMap<String, Any>) {
        when (expr) {
            is EqualsExpr -> map[expr.field] = expr.value
            is LikeExpr -> map[expr.field + LIKE_MARK] = expr.value
            is AndExpr -> {
                collect(expr.left, map)
                collect(expr.right, map)
            }
            is OrExpr -> throw IllegalArgumentException("OR не поддерживается в byProperties.")
            else -> throw IllegalArgumentException("Неизвестный тип выражения: $expr")
        }
    }
}