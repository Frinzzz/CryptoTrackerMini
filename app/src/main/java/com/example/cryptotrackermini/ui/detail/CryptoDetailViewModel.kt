package com.example.cryptotrackermini.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptotrackermini.domain.usecase.GetCryptoDetailUseCase
import com.example.cryptotrackermini.domain.usecase.IsCryptoFavoriteUseCase
import com.example.cryptotrackermini.domain.usecase.ToggleFavoriteCryptoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoDetailViewModel(
    private val cryptoId: String,
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase,
    private val isCryptoFavoriteUseCase: IsCryptoFavoriteUseCase,
    private val toggleFavoriteCryptoUseCase: ToggleFavoriteCryptoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CryptoDetailUiState(isLoading = true))
    val uiState: StateFlow<CryptoDetailUiState> = _uiState.asStateFlow()

    init { loadDetail() }

    fun loadDetail() {
        viewModelScope.launch {
            _uiState.value = CryptoDetailUiState(isLoading = true)
            try {
                val detail = withContext(Dispatchers.IO) { getCryptoDetailUseCase(cryptoId) }
                val favorite = withContext(Dispatchers.IO) { isCryptoFavoriteUseCase(cryptoId) }
                _uiState.value = CryptoDetailUiState(detail = detail, isFavorite = favorite)
            } catch (e: Exception) {
                _uiState.value = CryptoDetailUiState(errorMessage = e.message ?: "Errore durante il caricamento")
            }
        }
    }

    fun toggleFavorite() {
        val detail = _uiState.value.detail ?: return
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { toggleFavoriteCryptoUseCase(detail) }
                val favorite = withContext(Dispatchers.IO) { isCryptoFavoriteUseCase(detail.id) }
                _uiState.value = _uiState.value.copy(isFavorite = favorite)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message ?: "Errore nel salvataggio")
            }
        }
    }

    companion object {
        fun factory(
            cryptoId: String,
            getDetail: GetCryptoDetailUseCase,
            isFavorite: IsCryptoFavoriteUseCase,
            toggleFavorite: ToggleFavoriteCryptoUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CryptoDetailViewModel(cryptoId, getDetail, isFavorite, toggleFavorite) as T
            }
        }
    }
}
