package com.example.cryptotrackermini.ui.i18n

data class UiStrings(
    val appName: String,
    val openFavorites: String,
    val topCryptoSubtitle: String,
    val unknownError: String,
    val noCryptoFound: String,
    val retry: String,
    val detailTitle: String,
    val price: String,
    val marketCapRank: String,
    val marketCap: String,
    val change24h: String,
    val removeFromFavorites: String,
    val saveToFavorites: String,
    val back: String,
    val backToHome: String,
    val description: String,
    val descriptionUnavailable: String,
    val favoritesTitle: String,
    val emptyFavorites: String,
    val error: String,
    val notAvailable: String,
    val language: String
)

object AppStrings {
    fun of(language: AppLanguage): UiStrings = when (language) {
        AppLanguage.IT -> UiStrings(
            appName = "CryptoTracker Mini",
            openFavorites = "Apri preferiti",
            topCryptoSubtitle = "Top crypto per capitalizzazione di mercato",
            unknownError = "Errore sconosciuto",
            noCryptoFound = "Nessuna criptovaluta trovata",
            retry = "Riprova",
            detailTitle = "Dettaglio",
            price = "Prezzo",
            marketCapRank = "Market cap rank",
            marketCap = "Market cap",
            change24h = "Variazione 24h",
            removeFromFavorites = "Rimuovi dai preferiti",
            saveToFavorites = "Salva nei preferiti",
            back = "Indietro",
            backToHome = "Torna alla Home",
            description = "Descrizione",
            descriptionUnavailable = "Descrizione non disponibile.",
            favoritesTitle = "Preferiti",
            emptyFavorites = "Non hai ancora salvato criptovalute preferite.",
            error = "Errore",
            notAvailable = "N/D",
            language = "Lingua"
        )

        AppLanguage.EN -> UiStrings(
            appName = "CryptoTracker Mini",
            openFavorites = "Open favorites",
            topCryptoSubtitle = "Top crypto by market capitalization",
            unknownError = "Unknown error",
            noCryptoFound = "No cryptocurrencies found",
            retry = "Retry",
            detailTitle = "Details",
            price = "Price",
            marketCapRank = "Market cap rank",
            marketCap = "Market cap",
            change24h = "24h change",
            removeFromFavorites = "Remove from favorites",
            saveToFavorites = "Save to favorites",
            back = "Back",
            backToHome = "Back to Home",
            description = "Description",
            descriptionUnavailable = "Description not available.",
            favoritesTitle = "Favorites",
            emptyFavorites = "You have not saved any favorite cryptocurrencies yet.",
            error = "Error",
            notAvailable = "N/A",
            language = "Language"
        )
    }
}
