package com.example.goshopping.presentation.ui.screens.shoppinglist

import com.example.goshopping.domain.ShoppingItem

sealed interface ShoppingListIntent {
    data object Retry : ShoppingListIntent
    data class AddToCart(val shoppingItem: ShoppingItem): ShoppingListIntent
}
