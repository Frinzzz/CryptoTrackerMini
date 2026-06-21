package com.example.cryptotrackermini.domain.usecase

import com.example.cryptotrackermini.domain.repository.CryptoRepository

class IsCryptoFavoriteUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke(id: String) = repository.isFavorite(id)
}
