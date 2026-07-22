package com.example_restaurant_web.backend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var name: String,
    @Column(name = "parent_id", insertable = false, updatable = false)
    var parentId: Long? = null,
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    var parent: CategoryEntity? = null,
    @Column(name = "sort_order")
    var sortOrder: Int = 0
)