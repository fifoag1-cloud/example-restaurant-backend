package com.example_restaurant_web.backend.controller

import com.example_restaurant_web.backend.entity.ReservationEntity
import com.example_restaurant_web.backend.model.Reservation
import com.example_restaurant_web.backend.model.StatusUpdateRequest
import com.example_restaurant_web.backend.repository.ReservationRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")
class ReservationController(private val reservationRepository: ReservationRepository) {

    @GetMapping
    fun getReservations(): List<ReservationEntity> {
        return reservationRepository.findAll()
    }

    @PostMapping
    fun createReservation(@RequestBody reservation: Reservation): ResponseEntity<Map<String, String>> {

        if (!reservation.isValid()) {
            return ResponseEntity.badRequest().body(
                mapOf("message" to "Invalid reservation data")
            )
        }

        val savedReservation = ReservationEntity(
            name = reservation.name,
            date = reservation.date,
            time = reservation.time,
            guests = reservation.guests,
            status = "PENDING"
        )

        reservationRepository.save(savedReservation)

        return ResponseEntity.ok(
            mapOf("message" to "Reservation saved for ${reservation.name}")
        )
    }

    @PutMapping("/{id}")
    fun updateReservation(@PathVariable id: Long, @RequestBody reservation: Reservation): ResponseEntity<Map<String, String>> {
        val existingReservation = reservationRepository.findById(id)

        if (existingReservation.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val entity = existingReservation.get()

        entity.name = reservation.name
        entity.date = reservation.date
        entity.time = reservation.time
        entity.guests = reservation.guests

        reservationRepository.save(entity)

        return ResponseEntity.ok(
            mapOf("message" to "Reservation updated")
        )
    }

    @DeleteMapping("/{id}")
    fun deleteReservation(@PathVariable id: Long): ResponseEntity<Map<String, String>> {
        if (!reservationRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        reservationRepository.deleteById(id)

        return ResponseEntity.ok(
            mapOf("message" to "Reservation deleted")
        )
    }


    @PutMapping("/{id}/status")
    fun updateStatus(@PathVariable id: Long, @RequestBody request: StatusUpdateRequest): ResponseEntity<Map<String, String>> {
        val reservationOptional = reservationRepository.findById(id)

        if (reservationOptional.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val reservation = reservationOptional.get()

        reservation.status = request.status

        reservationRepository.save(reservation)

        return ResponseEntity.ok(
            mapOf("message" to "Status updated")
        )
    }
}