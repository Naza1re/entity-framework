package com.kotlin.entityframework.ql.parser

import com.kotlin.entityframework.exception.QlParseException
import com.kotlin.entityframework.ql.QlOperators
import com.kotlin.entityframework.ql.expression.*

object QlParser {

    fun parse(input: String): QlExpression {
        val tokens = tokenize(input)
        return parseTokens(tokens)
    }

    private fun tokenize(input: String): List<String> {
        val regex = Regex("\\s*(and|or|like|=|'[^']*'|\\w+)\\s*", RegexOption.IGNORE_CASE)
        return regex.findAll(input).map { it.groupValues[1].trim() }.toList()
    }

    private fun parseTokens(tokens: List<String>): QlExpression {
        // OR
        val orIndex = tokens.indexOfFirst { it.equals(QlOperators.OR, ignoreCase = true) }
        if (orIndex != -1) {
            val left = parseTokens(tokens.subList(0, orIndex))
            val right = parseTokens(tokens.subList(orIndex + 1, tokens.size))
            return OrExpr(left, right)
        }

        // AND
        val andIndex = tokens.indexOfFirst { it.equals(QlOperators.AND, ignoreCase = true) }
        if (andIndex != -1) {
            val left = parseTokens(tokens.subList(0, andIndex))
            val right = parseTokens(tokens.subList(andIndex + 1, tokens.size))
            return AndExpr(left, right)
        }

        //  field = 'value'
        if (tokens.size == 3 && tokens[1] == QlOperators.EQUALS) {
            val field = tokens[0]
            val value = tokens[2].removeSurrounding("'")
            return EqualsExpr(field, value)
        }

        // field like '%value%'
        if (tokens.size == 3 && tokens[1] == QlOperators.LIKE) {
            val field = tokens[0]
            val value = "%${tokens[2].removeSurrounding("'")}%"
            return LikeExpr(field, value)
        }

        throw QlParseException("Некорректное выражение: ${tokens.joinToString(" ")}")
    }
}