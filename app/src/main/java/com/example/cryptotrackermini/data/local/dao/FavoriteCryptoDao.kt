package com.example.cryptotrackermini.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptotrackermini.data.local.entity.FavoriteCryptoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCryptoDao {
    @Query("SELECT * FROM favorite_cryptos ORDER BY name ASC")
    fun observeFavorites(): Flow<List<FavoriteCryptoEntity>>

    @Query("SELECT * FROM favorite_cryptos WHERE id = :id LIMIT 1")
    suspend fun getFavoriteById(id: String): FavoriteCryptoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteCryptoEntity)

    @Query("DELETE FROM favorite_cryptos WHERE id = :id")
    suspend fun deleteFavorite(id: String)
}
