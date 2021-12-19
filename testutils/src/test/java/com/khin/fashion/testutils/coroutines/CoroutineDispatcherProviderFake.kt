package com.khin.fashion.testutils.coroutines

import com.khin.fashion.core.domain.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers

object CoroutineDispatcherProviderFake : CoroutineDispatcherProvider {
    override fun io() = Dispatchers.Unconfined
    override fun main() = Dispatchers.Unconfined
}
