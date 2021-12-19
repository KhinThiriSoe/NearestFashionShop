package com.khin.fashion.shop.data.remote

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("floormap")
    val floormap: Floormap,
    @SerializedName("store")
    val store: Store
)