package com.example.cryptotrackermini.domain.model

data class CryptoDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String?,
    val description: String?,
    val currentPrice: Double?,
    val marketCapRank: Int?,
    val marketCap: Double?,
    val priceChangePercentage24h: Double?
)
