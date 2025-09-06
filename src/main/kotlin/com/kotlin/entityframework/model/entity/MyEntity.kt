package com.kotlin.entityframework.model.entity

import com.kotlin.entityframework.model.type.EntityType
import jakarta.persistence.*
import jakarta.persistence.Entity
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.Type

@Entity
@Table(name = "entity")
data class MyEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    val name: String,

    val number: String,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val properties: Map<String, Any>? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id")
    val entityType: EntityType

)