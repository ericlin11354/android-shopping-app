package com.example.goshopping.data.repositories

import com.example.goshopping.domain.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRepository {
    val shoppingListResultStream: Flow<Result<List<ShoppingItem>>>
    suspend fun fetchShoppingItems()
    fun getShoppingItem(index: Int): ShoppingItem?
    suspend fun markShoppingItemAsAddedToCart(shoppingItem: ShoppingItem)
}
