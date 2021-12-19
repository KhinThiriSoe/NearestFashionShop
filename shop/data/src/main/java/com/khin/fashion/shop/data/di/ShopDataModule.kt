package com.khin.fashion.shop.data.di

import com.khin.fashion.core.data.di.BASIC_COROUTINE_ADAPTER
import com.khin.fashion.core.data.di.BASIC_GSON_FACTORY
import com.khin.fashion.core.data.network.RetrofitClientFactory
import com.khin.fashion.shop.data.ApiConfigs.BASE_URL
import com.khin.fashion.shop.data.remote.ShopService
import com.khin.fashion.shop.data.repository.ShopRepositoryImpl
import com.khin.fashion.shop.domain.repository.ShopRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val shopDataModule = module {

    single<ShopService> {
        RetrofitClientFactory(
            url = BASE_URL,
            okHttpClient = get(),
            converterFactory = get(named(BASIC_GSON_FACTORY)),
            callAdapterFactory = get(named(BASIC_COROUTINE_ADAPTER))
        ).build()
    }

    single<ShopRepository> { ShopRepositoryImpl(shopService = get()) }
}
