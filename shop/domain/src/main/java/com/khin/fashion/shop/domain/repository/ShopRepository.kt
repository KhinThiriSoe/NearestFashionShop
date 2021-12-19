package com.khin.fashion.shop.domain.repository

import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.shop.domain.LocalPickup

interface ShopRepository {
    suspend fun getShops(): RepositoryResult<List<LocalPickup>>
}