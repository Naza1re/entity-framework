package com.kotlin.entityframework.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long, val name: String) {
}