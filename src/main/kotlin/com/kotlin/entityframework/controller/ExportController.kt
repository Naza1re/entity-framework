package com.kotlin.entityframework.controller

import com.kotlin.entityframework.dto.entity.request.ExportRequest
import com.kotlin.entityframework.service.ExportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0.1/export")
class ExportController(
    private val exportService: ExportService
) {

    @PostMapping("/entities")
    fun export(@RequestBody exportRequest: ExportRequest) : ResponseEntity<ByteArray> {
        val excelData = exportService.exportEntities(exportRequest)
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=entities.xlsx")
            .body(excelData)
    }
}
