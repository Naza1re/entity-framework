package com.kotlin.entityframework.export

import com.kotlin.entityframework.model.entity.Entity
import org.springframework.stereotype.Component

@Component
class UpdatedAtFieldProvider : FieldProvider {
    override fun match(code: String): Boolean {
        return code == "updatedAt"
    }

    override fun getValue(entity: Entity): String {
        return entity.updatedAt.toString()
    }
}