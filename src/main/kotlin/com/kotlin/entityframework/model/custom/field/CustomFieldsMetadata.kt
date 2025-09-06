package com.kotlin.entityframework.model.custom.field

import jakarta.persistence.*
import jakarta.persistence.Entity

@Entity
data class CustomFieldsMetadata(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "custom_field_id")
    val customField: CustomField,

    @Enumerated(EnumType.STRING)
    val type: CustomFieldType
)
