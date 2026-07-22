package com.example_restaurant_web.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var name: String,
    @Column(name = "parent_id")
    var parentId: Long? = null,
    @Column(name = "sort_order")
    var sortOrder: Int = 0
)