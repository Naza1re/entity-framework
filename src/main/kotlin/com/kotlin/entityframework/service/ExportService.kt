package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.entity.request.ExportRequest

interface ExportService {
    fun exportEntities(exportRequest: ExportRequest): ByteArray
}