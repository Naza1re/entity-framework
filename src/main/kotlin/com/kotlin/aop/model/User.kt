package com.kotlin.aop.model

import com.kotlin.aop.model.entity.MyEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long, val name: String) {
}