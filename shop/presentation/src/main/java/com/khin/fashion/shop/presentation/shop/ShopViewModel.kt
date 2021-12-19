package com.khin.fashion.shop.presentation.shop

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khin.fashion.core.domain.coroutines.CoroutineDispatcherProvider
import com.khin.fashion.core.domain.entity.UseCaseResult
import com.khin.fashion.core.presentation.viewmodel.BaseViewModel
import com.khin.fashion.core.presentation.widget.OverlayViewState
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.usecase.GetShopsUseCase
import com.khin.fashion.shop.presentation.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ShopViewModel(
    private val getShopsUseCase: GetShopsUseCase,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel<ShopViewState, Unit>(
    coroutineDispatcherProvider,
    ShopViewState(
        emptyList(),
        OverlayViewState(isLoading = true),
    )
) {

    private var mainJob: Job? = null

    private var localPick: List<LocalPickup> = emptyList()
    val currentLocation = MutableLiveData<Location?>()

    fun load() {
        mainJob?.cancel()
        setViewState { copy(overlayViewState = OverlayViewState(isLoading = true)) }
        getShop()
    }

    private fun getShop() {
        mainJob = viewModelScope.launch {
            when (val result = getShopsUseCase.execute()) {
                is UseCaseResult.Success -> handleGetShopSuccess(result.value)
                is UseCaseResult.ConnectionError -> { showConnectionError() }
                is UseCaseResult.GenericError -> { showGenericError(result.message) }
            }
        }
    }

    private fun handleGetShopSuccess(_localPickup: List<LocalPickup>) {
        currentLocation.value?.let { location ->
            localPick = _localPickup.map {
                it.copy(
                    distance = getDistanceBetweenTwoPoints(
                        location.latitude,
                        location.longitude,
                        it.pick_latitude,
                        it.pick_longitude
                    )
                )
            }.sortedBy { it.distance }
        } ?: kotlin.run {
            localPick = _localPickup
        }
        setViewState {
            copy(
                localPickup = localPick,
                overlayViewState = OverlayViewState(isLoading = false)
            )
        }
    }

    private fun getDistanceBetweenTwoPoints(
        currentLat: Double,
        currentLong: Double,
        pickupLat: Double,
        pickLong: Double
    ): Float {
        val distance = FloatArray(2)
        Location.distanceBetween(
            currentLat, currentLong,
            pickupLat, pickLong, distance
        )
        return distance[0]
    }

    private fun showConnectionError() {
        setViewState {
            copy(
                overlayViewState = overlayViewState.copy(
                    isLoading = false,
                    messageRes = R.string.core_no_internet,
                    ctaRes = R.string.core_try_again,
                    imageRes = R.drawable.ic_no_connection,
                    ctaAction = ::load
                )
            )
        }
    }

    private fun showGenericError(message: String?) {
        setViewState {
            val newOverlayViewState = message?.let {
                overlayViewState.copy(
                    isLoading = false,
                    message = message,
                    ctaRes = R.string.core_try_again,
                    imageRes = R.drawable.ic_error,
                    ctaAction = ::load
                )
            } ?: kotlin.run {
                overlayViewState.copy(
                    isLoading = false,
                    messageRes = R.string.core_generic_error,
                    ctaRes = R.string.core_try_again,
                    imageRes = R.drawable.ic_error,
                    ctaAction = ::load
                )
            }
            copy(
                overlayViewState = newOverlayViewState
            )
        }
    }

}