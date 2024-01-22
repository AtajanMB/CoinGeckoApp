package com.example.coingeckoapp.presentation.coin

import com.example.coingeckoapp.domain.models.Coin
import com.example.coingeckoapp.domain.models.CoinDetails

data class CoinState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetails? = null,
    val error: String = ""
)
