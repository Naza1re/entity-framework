package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.exception.OperationNotSupportedException
import com.kotlin.entityframework.model.custom.field.CustomFieldType
import com.kotlin.entityframework.ql.expression.AndExpr
import com.kotlin.entityframework.ql.expression.EqualsExpr
import com.kotlin.entityframework.ql.expression.GreaterThanExpr
import com.kotlin.entityframework.ql.expression.LikeExpr
import com.kotlin.entityframework.ql.expression.OrExpr
import com.kotlin.entityframework.service.CustomFieldService
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

class JpaSpecificationVisitor<T>(
    private val root: Root<T>,
    private val query: CriteriaQuery<*>?,
    private val cb: CriteriaBuilder,
    private val customFieldService: CustomFieldService // Прокидываем сервис
) : QlVisitor<Predicate> {

    override fun visit(expr: EqualsExpr): Predicate {
        val (path, codePredicate) = buildPathAndPredicate(expr.field)
        val equalsPredicate = cb.equal(path, expr.value)
        return if (codePredicate != null) cb.and(codePredicate, equalsPredicate) else equalsPredicate
    }

    override fun visit(expr: LikeExpr): Predicate {
        val (path, codePredicate) = buildPathAndPredicate(expr.field)
        val likePredicate = cb.like(path, "%${expr.value}%")
        return if (codePredicate != null) cb.and(codePredicate, likePredicate) else likePredicate
    }

    override fun visit(expr: GreaterThanExpr): Predicate {
        val (path, codePredicate) = buildPathAndPredicate(expr.field)
        if(codePredicate != null){
            val customFieldType = customFieldService.getCustomFieldTypeByCode(expr.field)
            if(customFieldType != CustomFieldType.NUMBER)
                throw OperationNotSupportedException("field with code ${expr.field} have a type $customFieldType and not support operation '>'")
        }
        val numericPath = cb.function("to_number", Double::class.javaObjectType, path, cb.literal("999999999D"))
        val gtPredicate = cb.greaterThan(numericPath, expr.value.toDouble())
        return if (codePredicate != null) cb.and(codePredicate, gtPredicate) else gtPredicate
    }

    override fun visit(expr: OrExpr): Predicate =
        cb.or(expr.left.accept(this), expr.right.accept(this))

    override fun visit(expr: AndExpr): Predicate =
        cb.and(expr.left.accept(this), expr.right.accept(this))


    private fun buildPathAndPredicate(field: String): Pair<Expression<String>, Predicate?> {
        val isUuid = field.matches(Regex("""\p{XDigit}{8}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{4}-\p{XDigit}{12}"""))

        return if (isUuid) {
            query?.distinct(true)
            val customFieldJoin = root.join<Any, Any>("entityType")
                .join<Any, Any>("customFields")
                .join<Any, Any>("customField")

            val path = cb.function("jsonb_extract_path_text", String::class.java,
                root.get<String>("properties"), customFieldJoin.get<String>("name"))

            val codePredicate = cb.equal(customFieldJoin.get<String>("code"), field)
            path to codePredicate
        } else {
            val path = cb.function("jsonb_extract_path_text", String::class.java,
                root.get<String>("properties"), cb.literal(field))
            path to null
        }
    }
}
