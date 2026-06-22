package com.example.cryptotrackermini.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptotrackermini.domain.usecase.GetFavoriteCryptosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoriteCryptosUseCase: GetFavoriteCryptosUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getFavoriteCryptosUseCase().collect { favorites ->
                    _uiState.value = FavoritesUiState(cryptos = favorites)
                }
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState(errorMessage = e.message ?: "Errore durante la lettura dei preferiti")
            }
        }
    }

    companion object {
        fun factory(useCase: GetFavoriteCryptosUseCase): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return FavoritesViewModel(useCase) as T
                }
            }
    }
}
