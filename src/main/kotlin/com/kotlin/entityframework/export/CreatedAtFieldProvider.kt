package com.kotlin.entityframework.export

import com.kotlin.entityframework.model.entity.Entity
import org.springframework.stereotype.Component

@Component
class CreatedAtFieldProvider : FieldProvider {
    override fun match(code: String): Boolean {
        return code == "createdAt"
    }

    override fun getValue(entity: Entity): String {
        return entity.createdAt.toString()
    }
}