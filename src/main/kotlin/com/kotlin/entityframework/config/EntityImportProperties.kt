package com.kotlin.entityframework.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "entity.import")
@Component
class EntityImportProperties {

    var columns: List<ColumnMapping> = emptyList()

    data class ColumnMapping(
        var header: String? = "",
        var jsonKey: String? = ""
    )
}