package com.example.goshopping.presentation.ui.screens.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goshopping.data.repositories.ShoppingItemRepository
import com.example.goshopping.domain.ShoppingItem
import com.example.goshopping.presentation.ui.screens.shoppinglist.ShoppingListIntent.AddToCart
import com.example.goshopping.presentation.ui.screens.shoppinglist.ShoppingListIntent.Retry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(val shoppingItemRepository: ShoppingItemRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ShoppingListUiState>(ShoppingListUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            shoppingItemRepository.shoppingListResultStream.collect { shoppingListResult ->
                shoppingListResult.onSuccess {
                    _uiState.value = ShoppingListUiState.Ready(it)
                }.onFailure {
                    _uiState.value =
                        ShoppingListUiState.Error("No shopping items to show: ${it.message}")
                }
            }
        }

        fetchShoppingItems()
    }

    fun handleIntent(intent: ShoppingListIntent) {
        when (intent) {
            is Retry -> fetchShoppingItems()
            is AddToCart -> markShoppingItemAsAddedToCart(intent.shoppingItem)
        }
    }

    // Populate uiState with shopping item catalogue
    private fun fetchShoppingItems() {
        _uiState.value = ShoppingListUiState.Loading

        viewModelScope.launch {
            shoppingItemRepository.fetchShoppingItems()
        }

    }

    // Toggle shoppingItem's "Add To Card" icon
    private fun markShoppingItemAsAddedToCart(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingItemRepository.markShoppingItemAsAddedToCart(shoppingItem)
        }
    }
}

