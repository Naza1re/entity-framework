package com.kotlin.entityframework.export

import com.kotlin.entityframework.model.entity.Entity
import org.springframework.stereotype.Component

@Component
class DescriptionFieldProvider : FieldProvider  {
    override fun match(code: String): Boolean {
        return code == "description"
    }

    override fun getValue(entity: Entity): String {
        return entity.description
    }
}