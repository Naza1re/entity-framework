package com.kotlin.entityframework.model.entity

import com.kotlin.entityframework.model.type.EntityType
import jakarta.persistence.*
import jakarta.persistence.Entity
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.Type
import java.time.LocalDateTime

@Entity
@Table(name = "entity")
data class Entity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @Column(name = "number")
    val number: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    var properties: Map<String, Any>? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id")
    var entityType: EntityType
)