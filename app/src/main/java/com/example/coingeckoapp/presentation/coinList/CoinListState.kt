package com.example.coingeckoapp.presentation.coinList

import com.example.coingeckoapp.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val error: String = ""
)
