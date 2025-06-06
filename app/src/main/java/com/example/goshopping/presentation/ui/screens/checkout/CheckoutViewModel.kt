package com.example.goshopping.presentation.ui.screens.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goshopping.data.repositories.ShoppingItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    val shoppingItemRepository: ShoppingItemRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CheckoutUiState>(CheckoutUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            shoppingItemRepository.shoppingListResultStream.collect { shoppingListResult ->
                shoppingListResult.onSuccess {
                    _uiState.value = CheckoutUiState.Ready(it)
                }
            }
        }
    }
}

