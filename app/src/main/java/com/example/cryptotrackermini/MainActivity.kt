package com.example.cryptotrackermini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cryptotrackermini.ui.i18n.AppLanguage
import com.example.cryptotrackermini.ui.navigation.AppNavHost
import com.example.cryptotrackermini.ui.theme.CryptoTrackerMiniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as CryptoTrackerMiniApplication).appContainer
        val preferences = getSharedPreferences("app_settings", MODE_PRIVATE)

        setContent {
            var appLanguage by remember {
                mutableStateOf(
                    AppLanguage.fromCode(
                        preferences.getString("language", AppLanguage.IT.code)
                    )
                )
            }

            CryptoTrackerMiniTheme {
                AppNavHost(
                    container = container,
                    appLanguage = appLanguage,
                    onLanguageChange = { selectedLanguage ->
                        appLanguage = selectedLanguage
                        preferences.edit()
                            .putString("language", selectedLanguage.code)
                            .apply()
                    }
                )
            }
        }
    }
}