package com.kotlin.aop.model.entity

import com.kotlin.aop.model.custom.field.CustomField
import jakarta.persistence.*

@Entity
@Table(name = "entity_type")
data class EntityType(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,

    @OneToMany(mappedBy = "entityType", fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
    val customFields: MutableSet<CustomField> = mutableSetOf(),

    @OneToMany(mappedBy = "entityType", cascade = [CascadeType.ALL], orphanRemoval = true)
    val entities: List<MyEntity> = mutableListOf(),
) {
}