package com.khin.fashion.shop.domain

data class LocalPickup(
    val pick_active: Boolean,
    val pick_alias: String,
    val pick_address: String,
    val pick_city: String,
    val pick_latitude: Double = 0.0,
    val pick_longitude: Double = 0.0,
    val distance: Float = 0f
)