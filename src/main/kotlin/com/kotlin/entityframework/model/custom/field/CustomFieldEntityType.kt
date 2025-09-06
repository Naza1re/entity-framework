package com.kotlin.entityframework.model.custom.field

import com.kotlin.entityframework.model.type.EntityType
import jakarta.persistence.*

@Entity
@Table(name = "custom_fields_entity_type")
class CustomFieldEntityType(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val customField: CustomField,

    @ManyToOne(fetch = FetchType.LAZY)
    val entityType: EntityType

)