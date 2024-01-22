package com.example.coingeckoapp.domain.models

data class CoinDetails(
    val name: String,
    val image: String,
    val market_cap: Long,
    val price: Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double,
    val description: String
)
