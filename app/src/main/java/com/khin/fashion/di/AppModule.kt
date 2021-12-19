package com.khin.fashion.di

import com.khin.fashion.core.data.di.coreDataModule
import com.khin.fashion.core.domain.di.coreDomainModule
import com.khin.fashion.shop.data.di.shopDataModule
import com.khin.fashion.shop.presentation.di.shopPresentationModule
import com.khin.fashion.shop.domain.di.shopDomainModule

val shop = listOf(shopDataModule, shopDomainModule, shopPresentationModule)

val core = listOf(coreDataModule, coreDomainModule)

val appModule = core + shop
