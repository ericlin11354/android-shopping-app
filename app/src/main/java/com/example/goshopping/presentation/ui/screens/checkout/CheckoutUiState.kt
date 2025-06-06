package com.example.goshopping.presentation.ui.screens.checkout

import com.example.goshopping.domain.ShoppingItem

sealed interface CheckoutUiState {
    data object Loading : CheckoutUiState

    data class Ready(val shoppingItems: List<ShoppingItem>) : CheckoutUiState
}
