package com.example.cryptotrackermini.data.remote.dto

import com.squareup.moshi.Json

data class CryptoDetailDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: ImageDto?,
    val description: DescriptionDto?,
    @Json(name = "market_cap_rank") val marketCapRank: Int?,
    @Json(name = "market_data") val marketData: MarketDataDto?
)

data class ImageDto(
    val thumb: String?,
    val small: String?,
    val large: String?
)

data class DescriptionDto(
    val en: String?
)

data class MarketDataDto(
    @Json(name = "current_price") val currentPrice: Map<String, Double>?,
    @Json(name = "market_cap") val marketCap: Map<String, Double>?,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage24h: Double?
)
