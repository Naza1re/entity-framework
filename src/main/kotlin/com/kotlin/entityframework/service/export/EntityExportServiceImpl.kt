package com.kotlin.entityframework.service.export

import com.kotlin.entityframework.config.EntityImportProperties
import com.kotlin.entityframework.dto.entity.request.ExportRequest
import com.kotlin.entityframework.model.entity.Entity
import com.kotlin.entityframework.repository.entity.EntityRepository
import com.kotlin.entityframework.service.ExportService
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class EntityExportServiceImpl(
    private val importProperties: EntityImportProperties,
    private val entityRepository: EntityRepository
): ExportService {
    override fun exportEntities(exportRequest: ExportRequest): ByteArray {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Entities")

        val headers = importProperties.columns.map { it.header ?: it.jsonKey ?: "" }

        val headerRow = sheet.createRow(0)
        headers.forEachIndexed { index, header ->
            headerRow.createCell(index).setCellValue(header)
        }

        val entities: List<Entity> = entityRepository.findAll()

        entities.forEachIndexed { rowIndex, entity ->
            val row = sheet.createRow(rowIndex + 1)

            importProperties.columns.forEachIndexed { colIndex, column ->
                val value = entity.properties?.get(column.jsonKey) ?: ""
                row.createCell(colIndex).setCellValue(value.toString())
            }
        }

        val outputStream = ByteArrayOutputStream()
        workbook.write(outputStream)
        workbook.close()

        return outputStream.toByteArray()
    }
}
