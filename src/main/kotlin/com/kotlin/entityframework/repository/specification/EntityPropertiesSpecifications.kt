package com.kotlin.entityframework.repository.specification

import com.kotlin.entityframework.model.entity.Entity
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object EntityPropertiesSpecifications {

    private const val PROPERTIES = "properties"
    private const val EXTRACT_PATH_TEXT = "jsonb_extract_path_text"
    private const val LIKE_MARK = "_like"

    fun byProperties(filters: Map<String, Any>): Specification<Entity> {
        return Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            for ((key, value) in filters) {
                val isLike = key.endsWith(LIKE_MARK)
                val field = if (isLike) key.removeSuffix(LIKE_MARK) else key

                val jsonExtract = cb.function(
                    EXTRACT_PATH_TEXT,
                    String::class.java,
                    root.get<String>(PROPERTIES),
                    cb.literal(field)
                )

                if (isLike) {
                    predicates.add(cb.like(jsonExtract, value.toString()))
                } else {
                    predicates.add(cb.equal(jsonExtract, value))
                }
            }

            cb.and(*predicates.toTypedArray())
        }
    }
}
