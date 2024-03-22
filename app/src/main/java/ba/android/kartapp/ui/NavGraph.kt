package ba.android.kartapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.screen.homescreen.HomeScreen
import ba.android.kartapp.ui.screen.posts.cartscreen.CartScreen
import ba.android.kartapp.ui.screen.product.ProductScreen
import ba.android.kartapp.ui.screen.splashscreen.SplashScreen

object MainDestinations {
    const val SPLASHSCREEN = "splashscreen"
    const val HOME = "home"
    const val PRODUCTS = "products"
    const val CART = "cart"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SPLASHSCREEN
) {
    val actions = remember(navController) { MainActions(navController) }
    val bottomNavigation = listOf(
        NavigationIcons(
            R.drawable.ic_booked,
            actions.bottomNavigationHome,
            stringResource(R.string.home)
        ),
        NavigationIcons(
            R.drawable.ic_queue,
            actions.bottomNavigationProducts,
            stringResource(R.string.product_details)
        ),
        NavigationIcons(
            R.drawable.ic_product_cart,
            actions.bottomNavigationCart,
            stringResource(R.string.kart)
        )
    )

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.SPLASHSCREEN) {
            SplashScreen(
                navigateToHome = { navController.navigate(MainDestinations.HOME) }
            )
        }
        composable(MainDestinations.HOME) {
            HomeScreen(
                navigateToProductDetails = actions.navigateToProductDetails,
                bottomNavigation = bottomNavigation)
        }
        composable(MainDestinations.PRODUCTS) {
            ProductScreen(
                viewModel = hiltViewModel(),
                navigateToCart = actions.navigateToCart,
                bottomNavigation = bottomNavigation
                )
        }
        composable(MainDestinations.CART) {
            CartScreen(
                viewModel = hiltViewModel(),
                navigateToProductDetails = actions.navigateToProductDetails,
                bottomNavigation = bottomNavigation,
            )
        }
    }
}


class MainActions(navController: NavHostController) {
    val navigateToMain: () -> Unit = {
        navController.navigate(MainDestinations.HOME) {
            popUpTo(MainDestinations.HOME) {
                inclusive = true
            }
        }
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToCart: () -> Unit = {
        navController.navigate(MainDestinations.CART)
    }

    val navigateToProductDetails: () -> Unit = {
        navController.navigate(MainDestinations.PRODUCTS)
    }

    val bottomNavigationHome: () -> Unit = {
        if (navController.currentDestination?.route != MainDestinations.HOME)
            navController.navigate(MainDestinations.HOME) {
                launchSingleTop = true
                restoreState = true
                popUpTo(MainDestinations.HOME) {
                    saveState = true
                }
            }
    }

    val bottomNavigationProducts: () -> Unit = {
        if (navController.currentDestination?.route != MainDestinations.PRODUCTS)
            navController.navigate(MainDestinations.PRODUCTS) {
                launchSingleTop = true
                restoreState = true
                popUpTo(MainDestinations.HOME) {
                    saveState = true
                }
            }
    }

    val bottomNavigationCart: () -> Unit = {
        if (navController.currentDestination?.route != MainDestinations.CART)
            navController.navigate(MainDestinations.CART) {
                launchSingleTop = true
                restoreState = true
                popUpTo(MainDestinations.HOME) {
                    saveState = true
                }
            }
    }
}
