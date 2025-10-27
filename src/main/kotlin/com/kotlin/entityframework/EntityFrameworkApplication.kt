package com.kotlin.entityframework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class EntityFrameworkApplication

fun main(args: Array<String>) {
    runApplication<EntityFrameworkApplication>(*args)
}
