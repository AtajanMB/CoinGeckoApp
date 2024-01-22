package com.example.coingeckoapp.domain.usecase

import com.example.coingeckoapp.domain.models.CoinDetails
import com.example.coingeckoapp.domain.repository.CoinRepository
import com.example.coingeckoapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<ResponseState<CoinDetails>> = flow {
        try {
            emit(ResponseState.Loading())
            val coin = repository.getCoinById(id).toCoinDetail()
            emit(ResponseState.Success(coin))
        }catch (e: HttpException){
            emit(ResponseState.Error(message = e.localizedMessage?:"An Unexpected Error"))
        }catch (e: IOException){
            emit(ResponseState.Error(message = "Internet Exception"))
        }
    }
}