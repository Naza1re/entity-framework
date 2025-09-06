package com.kotlin.entityframework.model.custom.field

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import jakarta.persistence.CascadeType
import jakarta.persistence.Table

@Entity
@Table(name = "custom_field")
data class CustomField(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "code") val code: String,
    @Column(name = "min") val min: Int,
    @Column(name = "max") val max: Int,

    @OneToMany(mappedBy = "customField" , fetch = FetchType.LAZY)
    val customFieldToEntityTypes: List<CustomFieldEntityType> = mutableListOf(),

    @OneToOne(mappedBy = "customField", cascade = [CascadeType.ALL])
    val metadata: CustomFieldsMetadata? = null,

)
