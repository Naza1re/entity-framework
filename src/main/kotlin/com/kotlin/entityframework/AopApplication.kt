package com.kotlin.entityframework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class AopApplication

fun main(args: Array<String>) {
    runApplication<AopApplication>(*args)
}
