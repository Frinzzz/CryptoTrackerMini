package com.example.cryptotrackermini.ui.detail

import com.example.cryptotrackermini.domain.model.CryptoDetail

data class CryptoDetailUiState(
    val isLoading: Boolean = false,
    val detail: CryptoDetail? = null,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
)
