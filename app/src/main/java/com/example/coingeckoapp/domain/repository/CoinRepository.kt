package com.example.coingeckoapp.domain.repository

import com.example.coingeckoapp.data.data_source.dto.coinDetailDTO.CoinDetailDTO
import com.example.coingeckoapp.data.data_source.dto.coinListDTO.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page: String): CoinListDTO

    suspend fun getCoinById(id: String): CoinDetailDTO
}