package com.kotlin.aop.model.custom.field

import com.kotlin.aop.model.entity.EntityType
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id")
    val entityType: EntityType
) {
}