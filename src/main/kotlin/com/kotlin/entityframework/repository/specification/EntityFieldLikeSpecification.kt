package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.model.entity.MyEntity
import org.springframework.data.jpa.domain.Specification

object EntityFieldLikeSpecification {

    fun entityFieldLike(fieldName: String, keyword: String): Specification<MyEntity> {
        return Specification {
            root, _, cb ->
            if (keyword.isBlank()) {
                 cb.conjunction()
            } else {
                cb.like(
                    cb.lower(root.get(fieldName)),
                    "%${keyword.lowercase()}%")

            }
        }
    }


}