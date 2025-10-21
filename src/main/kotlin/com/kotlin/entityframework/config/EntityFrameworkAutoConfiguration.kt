package com.kotlin.entityframework.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@ComponentScan("com.kotlin.entityframework")
@EntityScan("com.kotlin.entityframework.model")
@EnableJpaRepositories("com.kotlin.entityframework.repository")
class EntityFrameworkAutoConfiguration