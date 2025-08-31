package com.kotlin.entityframework.model.custom.field

import jakarta.persistence.*

@Entity
data class CustomField(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val code: String,

    @OneToOne(mappedBy = "customField", cascade = [CascadeType.ALL])
    val metadata: CustomFieldsMetadata? = null,

    val min: Int,
    val max: Int,

    @OneToMany(mappedBy = "customField" , fetch = FetchType.LAZY)
    val customFieldToEntityTypes: List<CustomFieldEntityType> = mutableListOf()
) {
}