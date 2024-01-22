package com.example.coingeckoapp.presentation.coinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingeckoapp.domain.usecase.CoinListUseCase
import com.example.coingeckoapp.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinListUseCase: CoinListUseCase
) : ViewModel() {

    private val _coinListValue = MutableStateFlow(CoinListState())
    var coinListValue: StateFlow<CoinListState> = _coinListValue

    fun getAllCoins(page: String) = viewModelScope.launch(Dispatchers.IO) {
        coinListUseCase(page).collect {
            when (it) {
                is ResponseState.Success -> {
                    _coinListValue.value = CoinListState(coinList = it.data ?: emptyList())
                }

                is ResponseState.Loading -> {
                    _coinListValue.value = CoinListState(isLoading = true)
                }

                is ResponseState.Error -> {
                    _coinListValue.value =
                        CoinListState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }


}