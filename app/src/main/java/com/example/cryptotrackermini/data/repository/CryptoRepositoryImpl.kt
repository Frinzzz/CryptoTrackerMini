package com.example.cryptotrackermini.data.repository

import com.example.cryptotrackermini.data.local.dao.FavoriteCryptoDao
import com.example.cryptotrackermini.data.mapper.CryptoMapper
import com.example.cryptotrackermini.data.remote.api.CoinGeckoApi
import com.example.cryptotrackermini.domain.model.Crypto
import com.example.cryptotrackermini.domain.model.CryptoDetail
import com.example.cryptotrackermini.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptoRepositoryImpl(
    private val api: CoinGeckoApi,
    private val dao: FavoriteCryptoDao
) : CryptoRepository {

    override suspend fun getCryptoList(): List<Crypto> {
        return api.getMarkets().map(CryptoMapper::fromMarketDto)
    }

    override suspend fun getCryptoDetail(id: String): CryptoDetail {
        return CryptoMapper.fromDetailDto(api.getCoinDetail(id))
    }

    override fun observeFavorites(): Flow<List<Crypto>> {
        return dao.observeFavorites().map { list -> list.map(CryptoMapper::fromEntity) }
    }

    override suspend fun isFavorite(id: String): Boolean {
        return dao.getFavoriteById(id) != null
    }

    override suspend fun toggleFavorite(crypto: CryptoDetail) {
        if (isFavorite(crypto.id)) {
            dao.deleteFavorite(crypto.id)
        } else {
            dao.insertFavorite(CryptoMapper.detailToEntity(crypto))
        }
    }
}
