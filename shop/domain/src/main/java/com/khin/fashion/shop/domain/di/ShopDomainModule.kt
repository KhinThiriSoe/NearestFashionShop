package com.khin.fashion.shop.domain.di

import com.khin.fashion.shop.domain.usecase.GetShopsUseCase
import com.khin.fashion.shop.domain.usecase.GetShopsUseCaseImpl
import org.koin.dsl.module

val shopDomainModule = module {

    single<GetShopsUseCase> { GetShopsUseCaseImpl(repository = get()) }
}