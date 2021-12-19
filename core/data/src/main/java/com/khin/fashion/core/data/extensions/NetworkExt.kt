package com.khin.fashion.core.data.extensions

import com.google.gson.GsonBuilder
import com.khin.fashion.core.domain.entity.NetworkErrorResponse
import com.khin.fashion.core.domain.entity.RepositoryResult
import retrofit2.HttpException
import java.net.UnknownHostException

const val ERROR_BODY_PARSE_FAILURE = "Failed to parse error body"

suspend fun <T> wrapNetworkCall(networkCall: suspend () -> T): RepositoryResult<T> {
    return try {
        RepositoryResult.Success(networkCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> RepositoryResult.ConnectionError
            is HttpException -> {
                val networkErrorResponse = throwable.toNetworkErrorResponse()
                RepositoryResult.GenericError(networkErrorResponse)
            }
            else -> RepositoryResult.GenericError(null)
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun HttpException.toNetworkErrorResponse(): NetworkErrorResponse {

    val gson = GsonBuilder()
        .create()

    val apiError = this.response()?.errorBody()?.string()?.let {
        val jsonAdapter = gson.getAdapter(NetworkErrorResponse::class.java)
        try {
            jsonAdapter.fromJson(it)
        } catch (e: Exception) {
            null
        }
    } ?: NetworkErrorResponse(
        code = null,
        message = null,
        detail = ERROR_BODY_PARSE_FAILURE
    )
    return apiError
}