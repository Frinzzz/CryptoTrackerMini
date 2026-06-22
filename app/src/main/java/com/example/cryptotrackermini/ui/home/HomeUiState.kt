package com.example.cryptotrackermini.ui.home

import com.example.cryptotrackermini.domain.model.Crypto

data class HomeUiState(
    val isLoading: Boolean = false,
    val cryptos: List<Crypto> = emptyList(),
    val errorMessage: String? = null
)
