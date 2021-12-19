package com.khin.fashion.core.domain.entity

sealed class UseCaseResult<out T> {
    data class Success<T>(val value: T) : UseCaseResult<T>()
    data class GenericError(val message: String?) : UseCaseResult<Nothing>()
    object ConnectionError : UseCaseResult<Nothing>()
}