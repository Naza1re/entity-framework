package com.kotlin.entityframework.service.export

import com.kotlin.entityframework.config.EntityImportProperties
import com.kotlin.entityframework.dto.entity.request.ExportRequest
import com.kotlin.entityframework.export.FieldProvider
import com.kotlin.entityframework.repository.entity.EntityRepository
import com.kotlin.entityframework.repository.specification.SpecificationCreator
import com.kotlin.entityframework.service.ExportService
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class EntityExportServiceImpl(
    private val importProperties: EntityImportProperties,
    private val entityRepository: EntityRepository,
    private val fieldProviders: List<FieldProvider>,
): ExportService {

    companion object {
        private const val ENTITIES = "Entities"
    }

    override fun exportEntities(exportRequest: ExportRequest): ByteArray {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet(ENTITIES)

        val headers = importProperties.columns.map { it.header ?: it.jsonKey ?: "" }

        val boldFont = workbook.createFont().apply {
            bold = true
        }
        val boldStyle = workbook.createCellStyle().apply {
            setFont(boldFont)
        }

        val headerRow = sheet.createRow(0)
        headers.forEachIndexed { index, header ->
            val cell =  headerRow.createCell(index)
            cell.cellStyle = boldStyle
            cell.setCellValue(header)
        }
        val pageRequest = PageRequest.of(exportRequest.page, exportRequest.pageSize)
        val specification = SpecificationCreator.entitySpecificationCreate(exportRequest.query)

        val entities = entityRepository.findAll(specification, pageRequest)

        entities.forEachIndexed { rowIndex, entity ->
            val row = sheet.createRow(rowIndex + 1)

            importProperties.columns.forEachIndexed { colIndex, column ->

                val value = fieldProviders
                    .firstNotNullOfOrNull { provider ->
                        if (provider.match(column.jsonKey.toString())) {
                            provider.getValue(entity)
                        } else null
                    } ?: entity.properties?.get(column.jsonKey) ?: ""

                row.createCell(colIndex).setCellValue(value.toString())
            }
        }

        val outputStream = ByteArrayOutputStream()
        workbook.write(outputStream)
        workbook.close()

        return outputStream.toByteArray()
    }
}
