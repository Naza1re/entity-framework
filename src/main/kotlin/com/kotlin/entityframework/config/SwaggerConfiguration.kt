package com.kotlin.entityframework.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    companion object {
        private const val SWAGGER_TITLE = "Entity Framework API"
        private const val SWAGGER_DESCRIPTION = "REST API for managing entities"
        private const val SWAGGER_VERSION = "1.0.0"
    }

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI().info(
            Info()
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .version(SWAGGER_VERSION)
        )
}
