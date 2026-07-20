package com.example_restaurant_web.backend.repository

import com.example_restaurant_web.backend.entity.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<ReservationEntity, Long>