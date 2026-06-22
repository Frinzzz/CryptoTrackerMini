package com.example.cryptotrackermini.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cryptotrackermini.di.AppContainer
import com.example.cryptotrackermini.ui.components.ErrorContent
import com.example.cryptotrackermini.ui.components.LoadingContent
import com.example.cryptotrackermini.ui.i18n.UiStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailScreen(
    cryptoId: String,
    container: AppContainer,
    strings: UiStrings,
    onBack: () -> Unit
) {
    val viewModel: CryptoDetailViewModel = viewModel(
        key = cryptoId,
        factory = CryptoDetailViewModel.factory(
            cryptoId = cryptoId,
            getDetail = container.getCryptoDetailUseCase,
            isFavorite = container.isCryptoFavoriteUseCase,
            toggleFavorite = container.toggleFavoriteCryptoUseCase
        )
    )
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text(strings.detailTitle) }) }) { padding ->
        when {
            state.isLoading -> LoadingContent(modifier = Modifier.padding(padding))
            state.errorMessage != null && state.detail == null -> ErrorContent(
                message = state.errorMessage ?: strings.unknownError,
                onRetry = viewModel::loadDetail,
                modifier = Modifier.padding(padding),
                retryText = strings.retry
            )
            state.detail != null -> {
                val detail = state.detail!!
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = detail.imageUrl,
                            contentDescription = detail.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(detail.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            Text(detail.symbol, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                    Text("${strings.price}: ${detail.currentPrice?.let { "€ %.2f".format(it) } ?: strings.notAvailable}")
                    Text("${strings.marketCapRank}: ${detail.marketCapRank ?: strings.notAvailable}")
                    Text("${strings.marketCap}: ${detail.marketCap?.let { "€ %.0f".format(it) } ?: strings.notAvailable}")
                    Text("${strings.change24h}: ${detail.priceChangePercentage24h?.let { "%+.2f%%".format(it) } ?: strings.notAvailable}")
                    Button(onClick = viewModel::toggleFavorite, modifier = Modifier.fillMaxWidth()) {
                        Text(if (state.isFavorite) strings.removeFromFavorites else strings.saveToFavorites)
                    }
                    Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text(strings.back) }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(strings.description, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = detail.description?.takeIf { it.isNotBlank() } ?: strings.descriptionUnavailable,
                        maxLines = 12,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
