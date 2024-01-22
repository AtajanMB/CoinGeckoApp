package com.example.coingeckoapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coingeckoapp.databinding.ActivityMainBinding
import com.example.coingeckoapp.domain.models.Coin
import com.example.coingeckoapp.presentation.coinList.CoinListAdapter
import com.example.coingeckoapp.presentation.coinList.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var coinsAdapter: CoinListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val coinListViewModel: CoinListViewModel by viewModels()
    private var page: Int = 1
    private var tempCoinList = mutableListOf<Coin>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        coinListViewModel = ViewModelProvider(this@MainActivity)[CoinListViewModel::class.java]
        setupRecycler()
        binding.btnSort.setOnClickListener {
            tempCoinList.sortWith { o1, o2 -> o1.name.compareTo(o2.name) }
            coinsAdapter.submitList(tempCoinList)
        }
        coinListViewModel.getAllCoins(page.toString())
        callApi()
    }

    private fun setupRecycler() {
        layoutManager = GridLayoutManager(this, 2)
        coinsAdapter = CoinListAdapter()
        binding.rvCoins.adapter = coinsAdapter
        setupClickListener()
        binding.rvCoins.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    page++
                    coinListViewModel.getAllCoins(page.toString())
                    callApi()
                }
            }
        })

    }

    private fun setupClickListener() {
        coinsAdapter.onShopItemClickListener = {
//            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
//            startActivity(intent)

        }
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.IO).launch {
            coinListViewModel.coinListValue.collectLatest { coinListValue ->
                withContext(Dispatchers.Main) {
                    if (coinListValue.isLoading) {
                        binding.progressBar.visibility = VISIBLE
                    } else {
                        if (coinListValue.error.isNotBlank()) {
                            Toast.makeText(
                                this@MainActivity, coinListValue.error, Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            tempCoinList.addAll(coinListValue.coinList)
                            coinsAdapter.submitList(tempCoinList)
                            println(tempCoinList)
                        }
                        binding.progressBar.visibility = GONE
                    }
                }

            }
        }
    }
}