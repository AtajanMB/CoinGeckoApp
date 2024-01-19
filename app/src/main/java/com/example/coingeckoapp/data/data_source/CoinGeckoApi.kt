package com.example.coingeckoapp.data.data_source

import com.example.coingeckoapp.data.data_source.dto.coinDetailDTO.CoinDetailDTO
import com.example.coingeckoapp.data.data_source.dto.coinListDTO.CoinListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("/api/v3/coins/market?vs_currency=usd&order=market_cap_desc&per_page=100&sparkline=false")
    suspend fun getAllCoins(@Query("page")page:String):CoinListDTO

    @GET("api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id")id:String):CoinDetailDTO

}