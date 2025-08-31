package com.kotlin.entityframework.service.entity

import com.kotlin.entityframework.service.EntitySearchService
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class EntitySearchServiceImpl(
    private val entityManager: EntityManager,
) : EntitySearchService {





}