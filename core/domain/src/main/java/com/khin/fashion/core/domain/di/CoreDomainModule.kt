package com.khin.fashion.core.domain.di

import com.khin.fashion.core.domain.coroutines.CoroutineDispatcherProvider
import com.khin.fashion.core.domain.coroutines.CoroutineDispatcherProviderImpl
import org.koin.dsl.module

val coreDomainModule = module {
    single<CoroutineDispatcherProvider> { CoroutineDispatcherProviderImpl() }
}
