package com.kotlin.aop.controller

import com.kotlin.aop.dto.CreateRequest
import com.kotlin.aop.dto.SearchRequest
import com.kotlin.aop.model.entity.MyEntity
import com.kotlin.aop.service.entity.EntityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/entities")
class EntityController(
    private val entityService: EntityService
) {

    @GetMapping("/{number}")
    fun get(@PathVariable("number") number: String): MyEntity {
        return entityService.getByNumber(number)
    }

    @GetMapping("/search")
    fun search(@RequestBody searchRequest: SearchRequest): List<MyEntity> {
        return entityService.search(searchRequest)
    }

    @PostMapping
    fun add(@RequestBody createRequest: CreateRequest): MyEntity {
        return entityService.createEntity(createRequest)
    }


}