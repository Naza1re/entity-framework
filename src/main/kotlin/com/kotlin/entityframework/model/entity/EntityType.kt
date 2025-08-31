package com.kotlin.entityframework.model.entity

import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
import jakarta.persistence.*

@Entity
@Table(name = "entity_type")
data class EntityType(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val code: String,

    @OneToMany(mappedBy = "entityType", fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val customFields: MutableSet<CustomFieldEntityType> = mutableSetOf(),

    @OneToMany(mappedBy = "entityType", cascade = [CascadeType.ALL], orphanRemoval = true)
    val entities: List<MyEntity> = mutableListOf(),
) {
}