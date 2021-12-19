package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String
)