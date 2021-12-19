package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName
import com.khin.fashion.shop.domain.LocalPickup

data class RemoteShop(
    @SerializedName("number_of_new_locations")
    val number_of_new_locations: Int,
    @SerializedName("pickup")
    val pickup: List<Pickup>
)

fun Pickup.toLocalPickup() = LocalPickup(
    pick_active = active,
    pick_alias = alias,
    pick_address = address1,
    pick_city = city,
    pick_latitude = latitude,
    pick_longitude = longitude
)