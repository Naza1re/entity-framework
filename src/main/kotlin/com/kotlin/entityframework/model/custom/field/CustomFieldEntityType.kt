package com.kotlin.entityframework.model.custom.field

import com.kotlin.entityframework.model.type.EntityType
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.Entity

@Entity
@Table(name = "custom_fields_entity_type")
class CustomFieldEntityType(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val customField: CustomField,

    @ManyToOne(fetch = FetchType.LAZY)
    val entityType: EntityType,

    @Column(name = "required")
    val required: Boolean

)