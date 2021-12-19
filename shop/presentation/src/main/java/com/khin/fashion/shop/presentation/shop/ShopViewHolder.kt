package com.khin.fashion.shop.presentation.shop

import com.khin.fashion.core.presentation.viewholder.BaseViewHolder
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.presentation.databinding.ItemShopBinding

class ShopViewHolder(binding: ItemShopBinding) :
    BaseViewHolder(binding) {

    private val itemBinding: ItemShopBinding = binding

    fun bind(localPickup: LocalPickup) {

        itemBinding.textViewShopAlias.text = localPickup.pick_alias
        itemBinding.textViewShopAddress.text = localPickup.pick_address
        itemBinding.textViewShopCity.text = localPickup.pick_city

        itemBinding.executePendingBindings()
    }
}