package com.khin.fashion.core.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.khin.fashion.core.presentation.databinding.ViewOverlayBinding
import com.khin.fashion.core.presentation.extensions.gone
import com.khin.fashion.core.presentation.extensions.goneUnless
import com.khin.fashion.core.presentation.extensions.visible

data class OverlayViewState(
    val isLoading: Boolean,
    val message: String? = null,
    @StringRes val messageRes: Int? = null,
    @DrawableRes val imageRes: Int? = null,
    @StringRes val ctaRes: Int? = null,
    val ctaAction: (() -> Unit)? = null,
)

class OverlayView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val binding = ViewOverlayBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setViewState(viewState: OverlayViewState) = with(viewState) {
        if (shouldHide()) {
            gone()
        } else {
            visible()
            show()
        }
    }

    private fun OverlayViewState.show() {

        binding.progressbarOverlay.goneUnless(isLoading)

        imageRes?.let {
            binding.imageViewOverlay.visible()
            binding.imageViewOverlay.setImageResource(it)
        } ?: kotlin.run {
            binding.imageViewOverlay.gone()
        }

        val messageString = messageRes?.let { context.getString(it) } ?: message
        messageString?.let {
            binding.textViewOverlayMessage.visible()
            binding.textViewOverlayMessage.text = it
        } ?: kotlin.run {
            binding.textViewOverlayMessage.gone()
        }

        ctaRes?.let {
            binding.materialButtonOverlayCta.visible()
            binding.materialButtonOverlayCta.setText(it)
        } ?: kotlin.run {
            binding.materialButtonOverlayCta.gone()
        }

        binding.materialButtonOverlayCta.setOnClickListener {
            ctaAction?.invoke()
        }
    }

    private fun OverlayViewState.shouldHide() =
        !isLoading && message == null && messageRes == null && ctaRes == null && imageRes == null
}
