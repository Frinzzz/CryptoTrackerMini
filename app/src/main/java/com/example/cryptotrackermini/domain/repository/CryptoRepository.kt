package com.example.cryptotrackermini.domain.repository

import com.example.cryptotrackermini.domain.model.Crypto
import com.example.cryptotrackermini.domain.model.CryptoDetail
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getCryptoList(): List<Crypto>
    suspend fun getCryptoDetail(id: String): CryptoDetail
    fun observeFavorites(): Flow<List<Crypto>>
    suspend fun isFavorite(id: String): Boolean
    suspend fun toggleFavorite(crypto: CryptoDetail)
}
