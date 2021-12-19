package com.khin.fashion.shop.domain.usecase

import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.core.domain.entity.UseCaseResult
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.repository.ShopRepository

interface GetShopsUseCase {
    suspend fun execute(): UseCaseResult<List<LocalPickup>>
}

class GetShopsUseCaseImpl(private val repository: ShopRepository) :
    GetShopsUseCase {

    override suspend fun execute(): UseCaseResult<List<LocalPickup>> {
        return when (val result = repository.getShops()) {
            is RepositoryResult.Success -> UseCaseResult.Success(result.value)
            is RepositoryResult.ConnectionError -> UseCaseResult.ConnectionError
            is RepositoryResult.GenericError -> UseCaseResult.GenericError(result.networkErrorResponse?.message)
        }
    }
}