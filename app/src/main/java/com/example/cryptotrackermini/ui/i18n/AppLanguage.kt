package com.example.cryptotrackermini.ui.i18n

enum class AppLanguage(val code: String, val label: String) {
    IT("it", "IT"),
    EN("en", "EN");

    companion object {
        fun fromCode(code: String?): AppLanguage = entries.firstOrNull { it.code == code } ?: IT
    }
}
