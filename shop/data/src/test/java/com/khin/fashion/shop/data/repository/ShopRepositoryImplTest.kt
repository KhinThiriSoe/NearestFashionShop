package com.khin.fashion.shop.data.repository

import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.shop.data.remote.RemoteShop
import com.khin.fashion.shop.data.remote.ShopService
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.repository.ShopRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class ShopRepositoryImplTest {

    private val shopService: ShopService = mock()

    private val SUT: ShopRepository = ShopRepositoryImpl(shopService)

    @Test
    fun `success - getShop - pickup list returned`() = runBlockingTest {
        whenever(shopService.getShopList()).thenReturn(createRemoteShop())

        val result = SUT.getShops()

        assertEquals(RepositoryResult.Success(listOf<LocalPickup>()), result)
    }

    private fun createRemoteShop() = RemoteShop(
        number_of_new_locations = 5,
        emptyList()
    )
    // endregion helpers
}