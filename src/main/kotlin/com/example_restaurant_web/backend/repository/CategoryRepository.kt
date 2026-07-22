package com.example_restaurant_web.backend.repository

import com.example_restaurant_web.backend.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    fun findByParentIsNullOrderBySortOrder(): List<CategoryEntity>
    fun findByParentIdOrderBySortOrder(parentId: Long): List<CategoryEntity>
}