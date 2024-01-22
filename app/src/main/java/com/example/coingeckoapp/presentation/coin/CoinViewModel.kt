package com.example.coingeckoapp.presentation.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeckoapp.domain.usecase.CoinDetailUseCase
import com.example.coingeckoapp.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val detailUseCase: CoinDetailUseCase
) : ViewModel() {

    private val _coinValue = MutableStateFlow(CoinState())
    var coinValue: StateFlow<CoinState> = _coinValue

    fun getCoinDetail(id: String) = viewModelScope.launch(Dispatchers.IO) {
        detailUseCase(id).collect {
            when (it) {
                is ResponseState.Success -> {
                    _coinValue.value = CoinState(coinDetails = it.data)
                }

                is ResponseState.Loading -> {
                    _coinValue.value = CoinState(isLoading = true)
                }

                is ResponseState.Error -> {
                    _coinValue.value = CoinState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }
}