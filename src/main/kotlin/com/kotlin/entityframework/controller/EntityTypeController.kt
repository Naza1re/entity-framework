package com.kotlin.entityframework.controller

import com.kotlin.entityframework.dto.entity.type.response.EntityTypeResponse
import com.kotlin.entityframework.service.entity.EntityTypeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0.1/entity-types")
class EntityTypeController(
    private val entityTypeService: EntityTypeService
) {

    @GetMapping("/{code}")
    fun get(@PathVariable code: String) : ResponseEntity<EntityTypeResponse> {
        return ResponseEntity.ok(entityTypeService.getEntityType(code))
    }

}