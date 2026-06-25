package com.example.cryptotrackermini.ui.home

import com.example.cryptotrackermini.domain.model.Crypto

data class HomeUiState(
    val isLoading: Boolean = false,
    val allCryptos: List<Crypto> = emptyList(),
    val cryptos: List<Crypto> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null
)
