package com.khin.fashion.shop.data.repository

import com.khin.fashion.core.data.extensions.wrapNetworkCall
import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.shop.data.remote.ShopService
import com.khin.fashion.shop.data.remote.toLocalPickup
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.repository.ShopRepository

class ShopRepositoryImpl(
    private val shopService: ShopService
) : ShopRepository {
    override suspend fun getShops(): RepositoryResult<List<LocalPickup>> =
        wrapNetworkCall {
            val remoteShop = shopService.getShopList()
            remoteShop.pickup
                .filter { pickup ->
                            pickup.active &&
                            pickup.address1.isEmpty().not() &&
                            pickup.alias.isEmpty().not() &&
                            pickup.city.isEmpty().not()
                }
                .map { it.toLocalPickup() }
        }
}