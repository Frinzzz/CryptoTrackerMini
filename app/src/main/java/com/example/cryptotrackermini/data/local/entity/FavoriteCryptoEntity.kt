package com.example.cryptotrackermini.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cryptos")
data class FavoriteCryptoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String?,
    val currentPrice: Double?,
    val priceChangePercentage24h: Double?
)
