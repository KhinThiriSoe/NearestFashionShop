package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Pickup(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("address1")
    val address1: String,
    @SerializedName("address2")
    val address2: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("company")
    val company: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("feature")
    val feature: String,
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("floor_number")
    val floor_number: String,
    @SerializedName("floormap_image_path")
    val floormap_image_path: String,
    @SerializedName("hours")
    val hours: List<String>,
    @SerializedName("hours1")
    val hours1: String,
    @SerializedName("hours2")
    val hours2: String,
    @SerializedName("hours3")
    val hours3: String,
    @SerializedName("id_carrier")
    val id_carrier: Int,
    @SerializedName("id_country")
    val id_country: Int,
    @SerializedName("id_partner_store")
    val id_partner_store: Int,
    @SerializedName("id_pickup_location")
    val id_pickup_location: Int,
    @SerializedName("id_state")
    val id_state: Int,
    @SerializedName("id_zone")
    val id_zone: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("is_default_location")
    val is_default_location: Boolean,
    @SerializedName("is_featured")
    val is_featured: Boolean,
    @SerializedName("is_new_location")
    val is_new_location: Boolean,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("nearest_bts")
    val nearest_bts: String,
    @SerializedName("notable_area")
    val notable_area: Any,
    @SerializedName("nps_link")
    val nps_link: String,
    @SerializedName("payment_methods")
    val payment_methods: List<PaymentMethod>,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("store_image_path")
    val store_image_path: String,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String
)