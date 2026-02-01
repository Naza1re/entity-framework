package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.model.entity.Entity
import com.kotlin.entityframework.ql.expression.LikeExpr
import com.kotlin.entityframework.ql.expression.QlExpression
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import org.postgresql.util.ExpressionProperties
import org.springframework.data.jpa.domain.Specification

object EntityPropertiesSpecifications {

    private const val PROPERTIES = "properties"
    private const val EXTRACT_PATH_TEXT = "jsonb_extract_path_text"
}
