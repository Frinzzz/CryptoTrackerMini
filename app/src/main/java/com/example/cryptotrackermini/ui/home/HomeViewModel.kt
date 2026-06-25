package com.example.cryptotrackermini.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptotrackermini.domain.model.Crypto
import com.example.cryptotrackermini.domain.usecase.GetCryptoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCryptoListUseCase: GetCryptoListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init { loadCryptos() }

    fun loadCryptos() {
        viewModelScope.launch {
            val currentQuery = _uiState.value.searchQuery
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                val cryptos = withContext(Dispatchers.IO) { getCryptoListUseCase() }
                _uiState.value = HomeUiState(
                    allCryptos = cryptos,
                    cryptos = filterCryptos(cryptos, currentQuery),
                    searchQuery = currentQuery
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Loading error"
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val current = _uiState.value
        _uiState.value = current.copy(
            searchQuery = query,
            cryptos = filterCryptos(current.allCryptos, query)
        )
    }

    private fun filterCryptos(cryptos: List<Crypto>, query: String): List<Crypto> {
        val normalizedQuery = query.trim()
        if (normalizedQuery.isBlank()) return cryptos

        return cryptos.filter { crypto ->
            crypto.name.contains(normalizedQuery, ignoreCase = true) ||
                    crypto.symbol.contains(normalizedQuery, ignoreCase = true)
        }
    }

    companion object {
        fun factory(useCase: GetCryptoListUseCase): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(useCase) as T
                }
            }
    }
}
