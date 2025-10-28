package com.kotlin.entityframework.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.info.Info

@Configuration
class SwaggerConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI = OpenAPI().apply {
        info = Info().title("Entity framework API")
    }

}