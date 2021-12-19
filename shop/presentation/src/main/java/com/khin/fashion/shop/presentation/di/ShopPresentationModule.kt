package com.khin.fashion.shop.presentation.di

import com.khin.fashion.shop.presentation.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val shopPresentationModule = module {

    viewModel {
        ShopViewModel(
            getShopsUseCase = get(),
            coroutineDispatcherProvider = get()
        )
    }
}
