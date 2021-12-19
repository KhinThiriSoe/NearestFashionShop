package com.khin.fashion.shop.presentation.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.presentation.databinding.ItemShopBinding

class ShopAdapter : RecyclerView.Adapter<ShopViewHolder>() {

    var localPickup: List<LocalPickup> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = localPickup.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemShopBinding =
            ItemShopBinding.inflate(layoutInflater, parent, false)
        return ShopViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(localPickup[position])
    }
}