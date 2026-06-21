package com.example.cryptotrackermini.domain.usecase

import com.example.cryptotrackermini.domain.repository.CryptoRepository

class GetCryptoDetailUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke(id: String) = repository.getCryptoDetail(id)
}
