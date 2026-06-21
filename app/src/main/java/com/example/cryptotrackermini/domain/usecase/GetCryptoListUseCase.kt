package com.example.cryptotrackermini.domain.usecase

import com.example.cryptotrackermini.domain.repository.CryptoRepository

class GetCryptoListUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke() = repository.getCryptoList()
}
