package com.example.cryptotrackermini.data.mapper

import android.text.Html
import com.example.cryptotrackermini.data.local.entity.FavoriteCryptoEntity
import com.example.cryptotrackermini.data.remote.dto.CryptoDetailDto
import com.example.cryptotrackermini.data.remote.dto.CryptoMarketDto
import com.example.cryptotrackermini.domain.model.Crypto
import com.example.cryptotrackermini.domain.model.CryptoDetail

object CryptoMapper {
    fun fromMarketDto(dto: CryptoMarketDto): Crypto = Crypto(
        id = dto.id,
        name = dto.name,
        symbol = dto.symbol.uppercase(),
        imageUrl = dto.image,
        currentPrice = dto.currentPrice,
        priceChangePercentage24h = dto.priceChangePercentage24h
    )

    fun fromDetailDto(dto: CryptoDetailDto): CryptoDetail = CryptoDetail(
        id = dto.id,
        name = dto.name,
        symbol = dto.symbol.uppercase(),
        imageUrl = dto.image?.large ?: dto.image?.small ?: dto.image?.thumb,
        description = dto.description?.en?.let { cleanHtml(it) },
        currentPrice = dto.marketData?.currentPrice?.get("eur"),
        marketCapRank = dto.marketCapRank,
        marketCap = dto.marketData?.marketCap?.get("eur"),
        priceChangePercentage24h = dto.marketData?.priceChangePercentage24h
    )

    fun fromEntity(entity: FavoriteCryptoEntity): Crypto = Crypto(
        id = entity.id,
        name = entity.name,
        symbol = entity.symbol,
        imageUrl = entity.imageUrl,
        currentPrice = entity.currentPrice,
        priceChangePercentage24h = entity.priceChangePercentage24h
    )

    fun detailToEntity(detail: CryptoDetail): FavoriteCryptoEntity = FavoriteCryptoEntity(
        id = detail.id,
        name = detail.name,
        symbol = detail.symbol,
        imageUrl = detail.imageUrl,
        currentPrice = detail.currentPrice,
        priceChangePercentage24h = detail.priceChangePercentage24h
    )

    private fun cleanHtml(value: String): String {
        return Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY)
            .toString()
            .replace(Regex("\\s+"), " ")
            .trim()
    }
}
