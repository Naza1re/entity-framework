package com.kotlin.entityframework.ql.expression

import com.kotlin.entityframework.exception.QlParseException
import org.springframework.data.jpa.domain.Specification

data class GreaterThanExpr(
    val field: String,
    val value: String
) : QlExpression {


    override fun <T> toSpecification(): Specification<T> {
        val numericValue = value.toDoubleOrNull()
            ?: throw QlParseException("Значение '$value' не является числом  не поддерживает оператор '>'")
        return Specification { root, _, cb ->
            val jsonExtract = cb.function(
                "jsonb_extract_path_text",
                String::class.java,
                root.get<String>("properties"),
                cb.literal(field)
            )
            val numericPath = cb.function(
                "to_number",
                Double::class.javaObjectType,
                jsonExtract,
                cb.literal("999999999D")
            )
            cb.greaterThan(numericPath, numericValue)
        }
    }

}
