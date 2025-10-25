package com.kotlin.entityframework.controller

import com.kotlin.entityframework.dto.entity.request.ExportRequest
import com.kotlin.entityframework.service.ExportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0.1/export")
class ExportController(
    private val exportService: ExportService
) {


    @GetMapping("/entities")
    fun export(@RequestBody exportRequest: ExportRequest) : ResponseEntity<ByteArray> {
        return ResponseEntity.ok(exportService.exportEntities(exportRequest))
    }
}