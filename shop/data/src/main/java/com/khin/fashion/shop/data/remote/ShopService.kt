package com.khin.fashion.shop.data.remote

import com.khin.fashion.shop.data.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ShopService {

    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("/v3/pickup-locations")
    suspend fun getShopList(): RemoteShop
}
