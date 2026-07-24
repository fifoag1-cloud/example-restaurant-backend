package com.example_restaurant_web.backend.model

data class Reservation(
    val name: String,
    val date: String,
    val time: String,
    val guests: Int,
    val phone: String?,
    val specialRequest: String?
) {
    fun isValid(): Boolean {
        return name.isNotBlank() &&
                date.isNotBlank() &&
                time.isNotBlank() &&
                guests > 0
    }
}