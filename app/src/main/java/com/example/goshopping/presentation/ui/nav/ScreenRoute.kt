package com.example.goshopping.presentation.ui.nav

sealed interface ScreenRoute {
    val route: String

    data object ShoppingList : ScreenRoute {
        override val route = "shopping-list"
    }

    data object Checkout : ScreenRoute {
        override val route = "checkout"
    }

    data object Success: ScreenRoute {
        override val route = "success-screen"
    }

    data object Settings : ScreenRoute {
        override val route = "settings"
    }

}
