package com.kotlin.entityframework.service.export

import com.kotlin.entityframework.dto.entity.request.ExportRequest
import com.kotlin.entityframework.service.ExportService
import org.springframework.stereotype.Service

@Service
class EntityExportServiceImpl: ExportService {
    override fun exportEntities(exportRequest: ExportRequest): ByteArray {
        TODO("Not yet implemented")
    }
}