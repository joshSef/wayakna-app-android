package com.joshua.wayaknacatalog.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joshua.wayaknacatalog.ui.theme.ContactScreen
import com.joshua.wayaknacatalog.ui.theme.FavoritesScreen
import com.joshua.wayaknacatalog.ui.theme.HomeScreen
import com.joshua.wayaknacatalog.ui.theme.PackageDetailScreen
import com.joshua.wayaknacatalog.ui.theme.PackagesScreen

object Routes {
    const val HOME = "home"
    const val PACKAGES = "packages"
    const val FAVORITES = "favorites"
    const val CONTACT = "contact"
    const val PACKAGE_DETAIL = "packageDetail"
}

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onViewPackages = { nav.navigate(Routes.PACKAGES) },
                onViewFavorites = { nav.navigate(Routes.FAVORITES) },
                onContact = { nav.navigate(Routes.CONTACT) }
            )
        }
        composable(Routes.PACKAGES) {
            PackagesScreen(
                onBack = { nav.popBackStack() },
                onOpenFavorites = { nav.navigate(Routes.FAVORITES) },
                onOpenPackage = { id -> nav.navigate("${Routes.PACKAGE_DETAIL}/$id") }
            )
        }
        composable(Routes.FAVORITES) {
            FavoritesScreen(
                onBack = { nav.popBackStack() },
                onOpenPackage = { id -> nav.navigate("${Routes.PACKAGE_DETAIL}/$id") }
            )
        }
        composable(
            route = "${Routes.PACKAGE_DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id").orEmpty()
            PackageDetailScreen(
                packageId = id,
                onBack = { nav.popBackStack() },
                onContact = { nav.navigate(Routes.CONTACT) }
            )
        }
        composable(Routes.CONTACT) {
            ContactScreen(onBack = { nav.popBackStack() })
        }
    }
}
