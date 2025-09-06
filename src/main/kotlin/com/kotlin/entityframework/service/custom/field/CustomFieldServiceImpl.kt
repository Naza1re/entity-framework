package com.kotlin.entityframework.service.custom.field

import com.kotlin.entityframework.repository.custom.field.CustomFieldRepository
import com.kotlin.entityframework.service.CustomFieldService
import org.springframework.stereotype.Service

@Service
class CustomFieldServiceImpl(
    private val repository: CustomFieldRepository
) : CustomFieldService {
}