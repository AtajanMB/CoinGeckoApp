package com.example.coingeckoapp.presentation.coinList

import androidx.recyclerview.widget.DiffUtil
import com.example.coingeckoapp.domain.models.Coin

class CoinItemDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}