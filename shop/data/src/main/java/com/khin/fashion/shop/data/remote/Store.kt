package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("full_secondary")
    val full_secondary: String,
    @SerializedName("primary")
    val primary: Primary,
    @SerializedName("secondary")
    val secondary: String
)