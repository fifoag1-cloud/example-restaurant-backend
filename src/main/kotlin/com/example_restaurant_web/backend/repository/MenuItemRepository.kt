package com.example_restaurant_web.backend.repository

import com.example_restaurant_web.backend.entity.MenuItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository : JpaRepository<MenuItemEntity, Long>