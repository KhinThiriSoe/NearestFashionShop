package com.khin.fashion.core.domain.entity

sealed class RepositoryResult<out T> {
    data class Success<T>(val value: T) : RepositoryResult<T>()
    data class GenericError(val networkErrorResponse: NetworkErrorResponse?) : RepositoryResult<Nothing>()
    object ConnectionError : RepositoryResult<Nothing>()
}
