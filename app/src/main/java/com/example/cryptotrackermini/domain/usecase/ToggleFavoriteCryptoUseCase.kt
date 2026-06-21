package com.example.cryptotrackermini.domain.usecase

import com.example.cryptotrackermini.domain.model.CryptoDetail
import com.example.cryptotrackermini.domain.repository.CryptoRepository

class ToggleFavoriteCryptoUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke(crypto: CryptoDetail) = repository.toggleFavorite(crypto)
}
