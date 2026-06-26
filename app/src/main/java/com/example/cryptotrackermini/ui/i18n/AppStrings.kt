package com.example.cryptotrackermini.ui.i18n

data class UiStrings(
    val appName: String,
    val openFavorites: String,
    val topCryptoSubtitle: String,
    val unknownError: String,
    val noCryptoFound: String,
    val retry: String,
    val refresh: String,
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
    val language: String,
    val searchCryptoHint: String,
    val noSearchResults: String,
    val apiRateLimitError: String,
    val networkError: String,
    val genericLoadingError: String
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
            refresh = "Aggiorna",
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
            language = "Lingua",
            searchCryptoHint = "Cerca criptovaluta",
            noSearchResults = "Nessuna criptovaluta corrisponde alla ricerca.",
            apiRateLimitError = "Limite temporaneo dell’API raggiunto. Riprova tra qualche minuto.",
            networkError = "Connessione non disponibile. Controlla Internet e riprova.",
            genericLoadingError = "Errore durante il caricamento dei dati."
        )

        AppLanguage.EN -> UiStrings(
            appName = "CryptoTracker Mini",
            openFavorites = "Open favorites",
            topCryptoSubtitle = "Top crypto by market capitalization",
            unknownError = "Unknown error",
            noCryptoFound = "No cryptocurrencies found",
            retry = "Retry",
            refresh = "Refresh",
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
            language = "Language",
            searchCryptoHint = "Search cryptocurrency",
            noSearchResults = "No cryptocurrency matches your search.",
            apiRateLimitError = "API rate limit reached. Please try again in a few minutes.",
            networkError = "Network unavailable. Check your Internet connection and try again.",
            genericLoadingError = "Error while loading data."
        )
    }
}


fun UiStrings.readableApiError(rawMessage: String?): String {
    val message = rawMessage.orEmpty()
    return when {
        message.contains("429", ignoreCase = true) ||
                message.contains("Too Many Requests", ignoreCase = true) -> apiRateLimitError

        message.contains("Unable to resolve host", ignoreCase = true) ||
                message.contains("failed to connect", ignoreCase = true) ||
                message.contains("timeout", ignoreCase = true) -> networkError

        message.isBlank() -> genericLoadingError
        else -> message
    }
}
