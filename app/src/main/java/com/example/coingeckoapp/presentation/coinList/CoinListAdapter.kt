package com.example.coingeckoapp.presentation.coinList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.coingeckoapp.R
import com.example.coingeckoapp.databinding.ItemCoinBinding
import com.example.coingeckoapp.domain.models.Coin
import com.squareup.picasso.Picasso

class CoinListAdapter : ListAdapter<Coin, CoinListViewHolder>(CoinItemDiffCallback()) {

    var onShopItemClickListener: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {

        val binding = DataBindingUtil.inflate<ItemCoinBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_coin,
            parent,
            false
        )
        return CoinListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val coinItem = getItem(position)
        val binding = holder.binding

        binding.txtCoinName.text = coinItem.name
        Picasso.get().load(coinItem.image).into(binding.imgCoinImage)

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(coinItem)
        }
    }


}