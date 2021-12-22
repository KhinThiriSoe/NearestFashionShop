package com.khin.fashion.shop.domain.usecase

import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.core.domain.entity.UseCaseResult
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.repository.ShopRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetShopsUseCaseImplTest {

    private val repositoryMock: ShopRepository = mock()

    private val getShopsUseCase = GetShopsUseCaseImpl(repositoryMock)

    @Test
    fun `success - execute - success returned`() = runBlockingTest {
        whenever(repositoryMock.getShops()).thenAnswer {
            RepositoryResult.Success(listOf<LocalPickup>())
        }

        val result = getShopsUseCase.execute()

        assertEquals(UseCaseResult.Success(listOf<LocalPickup>()), result)

    }
}