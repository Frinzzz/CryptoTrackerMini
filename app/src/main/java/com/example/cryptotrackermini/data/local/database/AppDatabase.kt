package com.example.cryptotrackermini.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptotrackermini.data.local.dao.FavoriteCryptoDao
import com.example.cryptotrackermini.data.local.entity.FavoriteCryptoEntity

@Database(
    entities = [FavoriteCryptoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCryptoDao(): FavoriteCryptoDao
}
