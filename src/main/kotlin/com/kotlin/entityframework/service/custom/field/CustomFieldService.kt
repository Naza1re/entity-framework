package com.kotlin.entityframework.service.custom.field

import com.kotlin.entityframework.repository.custom.field.CustomFieldRepository
import org.springframework.stereotype.Service

@Service
class CustomFieldService(
    private val repository: CustomFieldRepository
) {
}