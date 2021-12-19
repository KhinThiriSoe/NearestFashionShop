package com.khin.fashion.core.domain.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatcherProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {
    override fun io() = Dispatchers.IO
    override fun main() = Dispatchers.Main
}
