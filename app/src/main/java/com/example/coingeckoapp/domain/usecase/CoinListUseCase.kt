package com.example.coingeckoapp.domain.usecase

import com.example.coingeckoapp.domain.models.Coin
import com.example.coingeckoapp.domain.repository.CoinRepository
import com.example.coingeckoapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(page: String): Flow<ResponseState<List<Coin>>> = flow {
        try {
            emit(ResponseState.Loading())
            val coins = repository.getAllCoins(page).map {
                it.toCoin()
            }
            println(coins)
            emit(ResponseState.Success(coins))
        } catch (e: HttpException) {
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(ResponseState.Error("InternetError"))
        }
    }
}