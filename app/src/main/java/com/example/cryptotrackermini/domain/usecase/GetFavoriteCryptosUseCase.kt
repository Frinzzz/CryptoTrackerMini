package com.example.cryptotrackermini.domain.usecase

import com.example.cryptotrackermini.domain.repository.CryptoRepository

class GetFavoriteCryptosUseCase(private val repository: CryptoRepository) {
    operator fun invoke() = repository.observeFavorites()
}
