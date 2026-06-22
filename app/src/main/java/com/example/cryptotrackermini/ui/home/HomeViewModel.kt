package com.example.cryptotrackermini.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
            _uiState.value = HomeUiState(isLoading = true)
            try {
                val cryptos = withContext(Dispatchers.IO) { getCryptoListUseCase() }
                _uiState.value = HomeUiState(cryptos = cryptos)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(errorMessage = e.message ?: "Errore durante il caricamento")
            }
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
