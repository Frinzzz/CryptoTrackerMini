package com.example.cryptotrackermini.ui.favorites

import com.example.cryptotrackermini.domain.model.Crypto

data class FavoritesUiState(
    val cryptos: List<Crypto> = emptyList(),
    val errorMessage: String? = null
)
