package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class PaymentMethod(
    @SerializedName("active")
    val active: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_partner_store")
    val id_partner_store: Int,
    @SerializedName("id_payment_type")
    val id_payment_type: Int,
    @SerializedName("is_new")
    val is_new: Boolean,
    @SerializedName("position")
    val position: Int
)