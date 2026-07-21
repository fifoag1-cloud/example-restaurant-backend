package com.example_restaurant_web.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "menu_items")
data class MenuItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var name: String,
    @Column(length = 500)
    var description: String = "",
    @Column(nullable = false)
    var price: Double = 0.0,
    var imageUrl: String = "",
    var featured: Boolean = false
)