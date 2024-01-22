package com.example.coingeckoapp.data.repository

import com.example.coingeckoapp.data.data_source.CoinGeckoApi
import com.example.coingeckoapp.data.data_source.dto.coinDetailDTO.CoinDetailDTO
import com.example.coingeckoapp.data.data_source.dto.coinListDTO.CoinListDTO
import com.example.coingeckoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinGeckoApi
) : CoinRepository {
    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinApi.getAllCoins(page = page)
    }

    override suspend fun getCoinById(id: String): CoinDetailDTO {
        return coinApi.getCoinById(id = id)
    }
}