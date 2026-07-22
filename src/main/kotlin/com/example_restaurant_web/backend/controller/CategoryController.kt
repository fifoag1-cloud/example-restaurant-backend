package com.example_restaurant_web.backend.controller

import com.example_restaurant_web.backend.entity.CategoryEntity
import com.example_restaurant_web.backend.repository.CategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryRepository: CategoryRepository
) {

    @GetMapping
    fun getAllCategories(): List<CategoryEntity> {
        return categoryRepository.findAll()
    }

    @GetMapping("/top-level")
    fun getTopLevelCategories(): List<CategoryEntity> {
        return categoryRepository.findByParentIdIsNullOrderBySortOrder()
    }

    @GetMapping("/{id}/subcategories")
    fun getSubcategories(@PathVariable id: Long): List<CategoryEntity> {
        return categoryRepository.findByParentIdOrderBySortOrder(id)
    }

    @PostMapping
    fun createCategory(@RequestBody category: CategoryEntity): CategoryEntity {
        return categoryRepository.save(category)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody updated: CategoryEntity): ResponseEntity<CategoryEntity> {
        val existing = categoryRepository.findById(id)
        return if (existing.isPresent) {
            val item = existing.get()
            item.name = updated.name
            item.sortOrder = updated.sortOrder
            item.parentId = updated.parentId
            ResponseEntity.ok(categoryRepository.save(item))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        return if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}