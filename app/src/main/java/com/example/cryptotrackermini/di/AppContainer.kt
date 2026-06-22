package com.example.cryptotrackermini.di

import android.content.Context
import androidx.room.Room
import com.example.cryptotrackermini.data.local.database.AppDatabase
import com.example.cryptotrackermini.data.remote.api.CoinGeckoApi
import com.example.cryptotrackermini.data.repository.CryptoRepositoryImpl
import com.example.cryptotrackermini.domain.repository.CryptoRepository
import com.example.cryptotrackermini.domain.usecase.GetCryptoDetailUseCase
import com.example.cryptotrackermini.domain.usecase.GetCryptoListUseCase
import com.example.cryptotrackermini.domain.usecase.GetFavoriteCryptosUseCase
import com.example.cryptotrackermini.domain.usecase.IsCryptoFavoriteUseCase
import com.example.cryptotrackermini.domain.usecase.ToggleFavoriteCryptoUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer(context: Context) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api: CoinGeckoApi = retrofit.create(CoinGeckoApi::class.java)

    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "crypto_tracker_mini.db"
    ).build()

    private val repository: CryptoRepository = CryptoRepositoryImpl(
        api = api,
        dao = database.favoriteCryptoDao()
    )

    val getCryptoListUseCase = GetCryptoListUseCase(repository)
    val getCryptoDetailUseCase = GetCryptoDetailUseCase(repository)
    val getFavoriteCryptosUseCase = GetFavoriteCryptosUseCase(repository)
    val toggleFavoriteCryptoUseCase = ToggleFavoriteCryptoUseCase(repository)
    val isCryptoFavoriteUseCase = IsCryptoFavoriteUseCase(repository)
}
