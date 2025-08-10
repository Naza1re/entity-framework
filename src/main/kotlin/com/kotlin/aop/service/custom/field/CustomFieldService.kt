package com.kotlin.aop.service.custom.field

import com.kotlin.aop.repository.custom.field.CustomFieldRepository
import org.springframework.stereotype.Service

@Service
class CustomFieldService(
    private val repository: CustomFieldRepository
) {
}