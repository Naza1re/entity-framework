package com.kotlin.entityframework.ql.expression

import com.kotlin.entityframework.exception.QlParseException
import org.springframework.data.jpa.domain.Specification

data class GreaterThanExpr(
    val field: String,
    val value: String
) : QlExpression {


    override fun <T> toSpecification(): Specification<T> {
        val numericValue = value.toDoubleOrNull()
            ?: throw QlParseException("Значение '$value' не является числом и не поддерживает оператор '>'")

        return Specification { root, query, cb ->
            val isUuid = field.matches(Regex("""\p{XDigit}{8}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{12}"""))
            val fieldKeyPath = if (isUuid) {
                query?.distinct(true)
                root.join<Any, Any>("entityType")
                    .join<Any, Any>("customFields")
                    .join<Any, Any>("customField")
                    .apply {
                    }
            } else null
            val fieldNameExpression = if (fieldKeyPath != null) {
                fieldKeyPath.get("name")
            } else {
                cb.literal(field)
            }
            val jsonExtract = cb.function(
                "jsonb_extract_path_text",
                String::class.java,
                root.get<String>("properties"),
                fieldNameExpression
            )
            val numericPath = cb.function(
                "to_number",
                Double::class.javaObjectType,
                jsonExtract,
                cb.literal("999999999D")
            )
            val greaterThanPredicate = cb.greaterThan(numericPath, numericValue)
            if (fieldKeyPath != null) {
                cb.and(cb.equal(fieldKeyPath.get<String>("code"), field), greaterThanPredicate)
            } else {
                greaterThanPredicate
            }
        }
    }


}
