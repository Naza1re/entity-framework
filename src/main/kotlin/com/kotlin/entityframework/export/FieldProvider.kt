package com.kotlin.entityframework.export

import com.kotlin.entityframework.model.entity.Entity

interface FieldProvider {
    fun match(code: String) : Boolean
    fun getValue(entity: Entity) : String
}