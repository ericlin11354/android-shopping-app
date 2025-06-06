package com.example.goshopping.presentation.ui.screens.shoppinglist

import com.example.goshopping.domain.ShoppingItem

sealed interface ShoppingListUiState {
    data object Loading : ShoppingListUiState

    data class Ready(val shoppingItems: List<ShoppingItem>) : ShoppingListUiState

    data class Error(val message: String) : ShoppingListUiState
}
