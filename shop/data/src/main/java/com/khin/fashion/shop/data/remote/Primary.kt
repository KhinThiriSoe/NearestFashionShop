package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Primary(
    @SerializedName("full_landscape")
    val full_landscape: String,
    @SerializedName("full_portrait")
    val full_portrait: String,
    @SerializedName("landscape")
    val landscape: String,
    @SerializedName("portrait")
    val portrait: String
)