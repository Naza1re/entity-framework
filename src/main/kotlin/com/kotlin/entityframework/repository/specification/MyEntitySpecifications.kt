package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.model.entity.MyEntity
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object MyEntitySpecifications {
    fun byProperties(filters: Map<String, Any>): Specification<MyEntity> {
        return Specification { root, query, cb ->
            val predicates = mutableListOf<Predicate>()
            for ((key, value) in filters) {
                val jsonExtract = cb.function(
                    "jsonb_extract_path_text",
                    String::class.java,
                    root.get<String>("properties"),
                    cb.literal(key)
                )
                predicates.add(cb.equal(jsonExtract, value))
            }
            cb.and(*predicates.toTypedArray())
        }
    }
}
