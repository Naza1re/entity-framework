package com.kotlin.entityframework.controller

import com.kotlin.entityframework.dto.type.request.EntityTypeCreateRequest
import com.kotlin.entityframework.dto.type.request.EntityTypeRequestSearch
import com.kotlin.entityframework.dto.type.request.EntityTypeUpdateRequest
import com.kotlin.entityframework.dto.type.response.EntityTypeResponse
import com.kotlin.entityframework.service.EntityTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0.1/entity-types")
class EntityTypeController(
    private val entityTypeService: EntityTypeService
) {

    @GetMapping("/{code}")
    fun get(@PathVariable code: String) : ResponseEntity<EntityTypeResponse> {
        return ResponseEntity.ok(entityTypeService.getEntityType(code))
    }

    @PostMapping
    fun create(@RequestBody createRequest: EntityTypeCreateRequest): ResponseEntity<EntityTypeResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(entityTypeService.createEntityType(createRequest))
    }

    @GetMapping
    fun getAll(@RequestBody searchRequest: EntityTypeRequestSearch): ResponseEntity<List<EntityTypeResponse>> {
        return ResponseEntity.ok(entityTypeService.getEntityTypesByRequest(searchRequest))
    }

    @PatchMapping("/{entityTypeCode}")
    fun update(@RequestBody updateRequest: EntityTypeUpdateRequest,
               @PathVariable entityTypeCode: String): ResponseEntity<EntityTypeResponse> {
        return ResponseEntity.ok(entityTypeService.updateEntityType(entityTypeCode, updateRequest))
    }

    @DeleteMapping("/{entityTypeCode}")
    fun delete(@PathVariable entityTypeCode: String): ResponseEntity<Void> {
        entityTypeService.deleteEntityType(entityTypeCode)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}