package com.example.cryptotrackermini.data.mapper

import com.example.cryptotrackermini.data.local.entity.FavoriteCryptoEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class CryptoMapperTest {
    @Test
    fun entityIsMappedToDomainModel() {
        val entity = FavoriteCryptoEntity(
            id = "bitcoin",
            name = "Bitcoin",
            symbol = "BTC",
            imageUrl = null,
            currentPrice = 50000.0,
            priceChangePercentage24h = 1.5
        )

        val crypto = CryptoMapper.fromEntity(entity)

        assertEquals("bitcoin", crypto.id)
        assertEquals("Bitcoin", crypto.name)
        assertEquals("BTC", crypto.symbol)
        assertEquals(50000.0, crypto.currentPrice ?: 0.0, 0.0)
    }
}
