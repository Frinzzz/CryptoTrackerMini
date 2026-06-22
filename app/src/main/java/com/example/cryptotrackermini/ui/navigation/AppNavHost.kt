package com.example.cryptotrackermini.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cryptotrackermini.di.AppContainer
import com.example.cryptotrackermini.ui.detail.CryptoDetailScreen
import com.example.cryptotrackermini.ui.favorites.FavoritesScreen
import com.example.cryptotrackermini.ui.home.HomeScreen
import com.example.cryptotrackermini.ui.i18n.AppLanguage
import com.example.cryptotrackermini.ui.i18n.AppStrings

private object Routes {
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val DETAIL = "detail/{cryptoId}"

    fun detail(cryptoId: String) = "detail/${Uri.encode(cryptoId)}"
}

@Composable
fun AppNavHost(
    container: AppContainer,
    appLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit
) {
    val navController = rememberNavController()
    val strings = AppStrings.of(appLanguage)

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                container = container,
                strings = strings,
                appLanguage = appLanguage,
                onLanguageChange = onLanguageChange,
                onOpenDetail = { id -> navController.navigate(Routes.detail(id)) },
                onOpenFavorites = { navController.navigate(Routes.FAVORITES) }
            )
        }
        composable(Routes.FAVORITES) {
            FavoritesScreen(
                container = container,
                strings = strings,
                onOpenDetail = { id -> navController.navigate(Routes.detail(id)) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("cryptoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val cryptoId = backStackEntry.arguments?.getString("cryptoId").orEmpty()
            CryptoDetailScreen(
                cryptoId = cryptoId,
                container = container,
                strings = strings,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
