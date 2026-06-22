package com.example.cryptotrackermini.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cryptotrackermini.di.AppContainer
import com.example.cryptotrackermini.ui.components.CryptoCard
import com.example.cryptotrackermini.ui.components.ErrorContent
import com.example.cryptotrackermini.ui.components.LoadingContent
import com.example.cryptotrackermini.ui.i18n.AppLanguage
import com.example.cryptotrackermini.ui.i18n.UiStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    container: AppContainer,
    strings: UiStrings,
    appLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    onOpenDetail: (String) -> Unit,
    onOpenFavorites: () -> Unit
) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.factory(container.getCryptoListUseCase)
    )
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(strings.appName) },
                actions = {
                    TextButton(
                        onClick = { onLanguageChange(AppLanguage.IT) },
                        enabled = appLanguage != AppLanguage.IT
                    ) { Text(AppLanguage.IT.label) }
                    TextButton(
                        onClick = { onLanguageChange(AppLanguage.EN) },
                        enabled = appLanguage != AppLanguage.EN
                    ) { Text(AppLanguage.EN.label) }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Button(
                onClick = onOpenFavorites,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) { Text(strings.openFavorites) }

            Text(
                text = strings.topCryptoSubtitle,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            when {
                state.isLoading -> LoadingContent()
                state.errorMessage != null -> ErrorContent(
                    message = state.errorMessage ?: strings.unknownError,
                    onRetry = viewModel::loadCryptos,
                    retryText = strings.retry
                )
                state.cryptos.isEmpty() -> ErrorContent(
                    message = strings.noCryptoFound,
                    retryText = strings.retry
                )
                else -> LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.cryptos, key = { it.id }) { crypto ->
                        CryptoCard(
                            crypto = crypto,
                            onClick = onOpenDetail,
                            notAvailableText = strings.notAvailable
                        )
                    }
                }
            }
        }
    }
}
