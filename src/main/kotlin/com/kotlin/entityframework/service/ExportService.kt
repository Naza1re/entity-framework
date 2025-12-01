package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.entity.request.ExportRequest

interface ExportService {
    /*
    Export entity list by ql request in XLSX file
    */
    fun exportEntities(exportRequest: ExportRequest): ByteArray
}