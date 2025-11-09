package com.kotlin.entityframework

import com.kotlin.entityframework.config.EntityTypeProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class EntityFrameworkApplication

fun main(args: Array<String>) {
    runApplication<EntityFrameworkApplication>(*args)
}
