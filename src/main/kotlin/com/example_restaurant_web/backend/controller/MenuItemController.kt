package com.example_restaurant_web.backend.controller

import com.example_restaurant_web.backend.entity.MenuItemEntity
import com.example_restaurant_web.backend.repository.MenuItemRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
class MenuItemController(
    private val menuItemRepository: MenuItemRepository
) {

    @GetMapping
    fun getAllMenuItems(): List<MenuItemEntity> {
        return menuItemRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getMenuItemById(@PathVariable id: Long): ResponseEntity<MenuItemEntity> {
        val item = menuItemRepository.findById(id)
        return if (item.isPresent) {
            ResponseEntity.ok(item.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/featured")
    fun getFeaturedMenuItems(): List<MenuItemEntity> {
        return menuItemRepository.findAll().filter { it.featured }
    }

    @GetMapping("/category/{categoryId}")
    fun getMenuItemsByCategory(@PathVariable categoryId: Long): List<MenuItemEntity> {
        return menuItemRepository.findAll().filter { it.categoryId == categoryId }
    }

    @PostMapping
    fun createMenuItem(@RequestBody menuItem: MenuItemEntity): MenuItemEntity {
        return menuItemRepository.save(menuItem)
    }

    @PutMapping("/{id}")
    fun updateMenuItem(@PathVariable id: Long, @RequestBody updatedItem: MenuItemEntity): ResponseEntity<MenuItemEntity> {
        val existingItem = menuItemRepository.findById(id)
        return if (existingItem.isPresent) {
            val item = existingItem.get()
            item.name = updatedItem.name
            item.description = updatedItem.description
            item.price = updatedItem.price
            item.imageUrl = updatedItem.imageUrl
            item.shownInMenu = updatedItem.shownInMenu
            item.featured = updatedItem.featured
            item.categoryId = updatedItem.categoryId
            item.subcategoryId = updatedItem.subcategoryId
            ResponseEntity.ok(menuItemRepository.save(item))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}/featured")
    fun toggleFeatured(@PathVariable id: Long): ResponseEntity<MenuItemEntity> {
        val existingItem = menuItemRepository.findById(id)
        return if (existingItem.isPresent) {
            val item = existingItem.get()
            item.featured = !item.featured
            ResponseEntity.ok(menuItemRepository.save(item))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: Long): ResponseEntity<Void> {
        return if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}