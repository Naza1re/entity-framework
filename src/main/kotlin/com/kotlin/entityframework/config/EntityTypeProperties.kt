package com.kotlin.entityframework.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "entity.type")
class EntityTypeProperties {
    var excludedCodes: List<String> = emptyList()
}