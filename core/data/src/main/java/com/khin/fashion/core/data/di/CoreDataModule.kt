package com.khin.fashion.core.data.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.khin.fashion.core.data.network.OkHttpClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

const val BASIC_GSON_FACTORY = "BASIC_GSON_FACTORY"
const val BASIC_COROUTINE_ADAPTER = "BASIC_COROUTINE_ADAPTER"

val coreDataModule = module {

    single { OkHttpClientFactory().build() }

    single<Converter.Factory>(named(BASIC_GSON_FACTORY)) {
        GsonConverterFactory.create()
    }

    single<CallAdapter.Factory>(named(BASIC_COROUTINE_ADAPTER)) {
        CoroutineCallAdapterFactory.invoke()
    }
}
