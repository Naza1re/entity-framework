package com.kotlin.entityframework.model.type

import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import com.kotlin.entityframework.model.entity.Entity

@jakarta.persistence.Entity
@Table(name = "entity_type")
data class EntityType(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "name") var name: String,
    @Column(name = "code") val code: String,

    @OneToMany(mappedBy = "entityType", fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val customFields: MutableSet<CustomFieldEntityType> = mutableSetOf(),

    @OneToMany(mappedBy = "entityType", cascade = [CascadeType.ALL], orphanRemoval = true)
    val entities: List<Entity> = mutableListOf(),
)
