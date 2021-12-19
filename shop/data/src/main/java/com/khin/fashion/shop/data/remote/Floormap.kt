package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Floormap(
    @SerializedName("full_main")
    val full_main: String,
    @SerializedName("full_zoomed")
    val full_zoomed: String,
    @SerializedName("main")
    val main: String,
    @SerializedName("zoomed")
    val zoomed: String
)