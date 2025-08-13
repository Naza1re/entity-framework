package com.kotlin.aop.controller

import com.kotlin.aop.dto.entity.request.CreateRequest
import com.kotlin.aop.dto.entity.request.UpdateRequest
import com.kotlin.aop.dto.entity.response.EntityResponse
import com.kotlin.aop.dto.entity.search.request.SearchRequest
import com.kotlin.aop.model.entity.MyEntity
import com.kotlin.aop.service.entity.EntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/entities")
class EntityController(
    private val entityService: EntityService
) {

    @GetMapping("/{number}")
    fun get(@PathVariable("number") number: String): ResponseEntity<EntityResponse> {
        return ResponseEntity.ok(entityService.getByNumber(number))
    }

    @PatchMapping("/{number}")
    fun patch(@PathVariable("number") number: String, updateRequest: UpdateRequest): ResponseEntity<EntityResponse> {
        return ResponseEntity.ok(entityService.updateEntity(number, updateRequest))
    }

    @PostMapping
    fun add(@RequestBody createRequest: CreateRequest): ResponseEntity<EntityResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(entityService.createEntity(createRequest))
    }

    @DeleteMapping("/{number}")
    fun delete(@PathVariable("number") number: String) : ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(entityService.deleteByNumber(number))
    }

    @GetMapping("/search")
    fun search(@RequestBody searchRequest: SearchRequest): List<MyEntity> {
        return entityService.search(searchRequest)
    }

}