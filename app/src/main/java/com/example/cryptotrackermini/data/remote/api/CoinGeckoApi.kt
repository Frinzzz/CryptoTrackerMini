package com.example.cryptotrackermini.data.remote.api

import com.example.cryptotrackermini.data.remote.dto.CryptoDetailDto
import com.example.cryptotrackermini.data.remote.dto.CryptoMarketDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getMarkets(
        @Query("vs_currency") currency: String = "eur",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<CryptoMarketDto>

    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false
    ): CryptoDetailDto
}
