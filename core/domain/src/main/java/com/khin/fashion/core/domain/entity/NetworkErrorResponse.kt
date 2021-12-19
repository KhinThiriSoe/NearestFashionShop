package com.khin.fashion.core.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Even though the error code is not described in the api spec, android needs to handle UI depends
 * on api response.
* */
data class NetworkErrorResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("detail")
    val detail: String?
)
