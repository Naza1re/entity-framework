package com.kotlin.entityframework.controller

import com.kotlin.entityframework.dto.entity.request.CreateRequest
import com.kotlin.entityframework.dto.entity.request.UpdateRequest
import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.dto.entity.search.request.QlSearchRequest
import com.kotlin.entityframework.dto.entity.search.request.SearchRequest
import com.kotlin.entityframework.service.EntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0.1/entities")
class EntityController(
    private val entityService: EntityService
) {

    @GetMapping
    fun all(@RequestBody searchRequest: SearchRequest): ResponseEntity<List<EntityResponse>> {
        return ResponseEntity.ok(entityService.getEntities(searchRequest))
    }

    @GetMapping("/{number}")
    fun get(@PathVariable("number") number: String): ResponseEntity<EntityResponse> {
        return ResponseEntity.ok(entityService.getByNumber(number))
    }

    @PatchMapping("/{number}")
    fun patch(@PathVariable("number") number: String, @RequestBody updateRequest: UpdateRequest): ResponseEntity<EntityResponse> {
        return ResponseEntity.ok(entityService.updateEntity(number, updateRequest))
    }

    @PostMapping
    fun create(@RequestBody createRequest: CreateRequest): ResponseEntity<EntityResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(entityService.createEntity(createRequest))
    }

    @DeleteMapping("/{number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("number") number: String) {
        entityService.deleteByNumber(number)
    }

    @GetMapping("/search")
    fun search(@RequestBody qlSearchRequest: QlSearchRequest): ResponseEntity<List<EntityResponse>> {
        return ResponseEntity.ok(entityService.search(qlSearchRequest))
    }
}
