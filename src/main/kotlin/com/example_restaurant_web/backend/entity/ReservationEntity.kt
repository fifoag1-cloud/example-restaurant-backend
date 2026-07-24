package com.example_restaurant_web.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "reservations")
data class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var date: String,
    var time: String,
    var guests: Int,
    var status: String = "PENDING",
    var phone: String? = null,
    var specialRequest: String? = null,
)