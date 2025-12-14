package com.kotlin.entityframework.model.custom.field

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

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
