package com.example.goshopping.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goshopping.presentation.ui.screens.checkout.CheckoutScreen
import com.example.goshopping.presentation.ui.screens.settings.SettingsScreen
import com.example.goshopping.presentation.ui.screens.shoppinglist.ShoppingListScreen
import com.example.goshopping.presentation.ui.screens.success.SuccessScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = ScreenRoute.ShoppingList.route) {
        composable(ScreenRoute.ShoppingList.route) {
            ShoppingListScreen(
                onCheckoutBtnClick = {
                    navController.navigate(ScreenRoute.Checkout.route)
                },
                onSettingsClick = {
                    navController.navigate(ScreenRoute.Settings.route)
                },
            )
        }

        composable(route = ScreenRoute.Checkout.route) {
            CheckoutScreen(
                onPlaceOrder = { navController.navigate(ScreenRoute.Success.route) },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(route = ScreenRoute.Success.route) {
            SuccessScreen(onNavigateHome = {
                navController.navigate(ScreenRoute.ShoppingList.route)
            })

        }

        composable(ScreenRoute.Settings.route) {
            SettingsScreen {
                navController.navigateUp()
            }
        }
    }
}