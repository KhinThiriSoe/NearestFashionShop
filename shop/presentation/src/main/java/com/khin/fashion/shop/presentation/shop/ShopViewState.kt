package com.khin.fashion.shop.presentation.shop

import com.khin.fashion.core.presentation.widget.OverlayViewState
import com.khin.fashion.shop.domain.LocalPickup

data class ShopViewState(
    val localPickup: List<LocalPickup>,
    val overlayViewState: OverlayViewState
)