package com.kotlin.entityframework.model.type

import com.kotlin.entityframework.model.custom.field.CustomFieldEntityType
import com.kotlin.entityframework.model.entity.MyEntity
import jakarta.persistence.*

@Entity
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
    val entities: List<MyEntity> = mutableListOf(),
)
