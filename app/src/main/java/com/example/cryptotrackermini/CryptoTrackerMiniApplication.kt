package com.example.cryptotrackermini

import android.app.Application
import com.example.cryptotrackermini.di.AppContainer

class CryptoTrackerMiniApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}