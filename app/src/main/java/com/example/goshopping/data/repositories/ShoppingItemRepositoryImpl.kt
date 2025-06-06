package com.example.goshopping.data.repositories

import com.example.goshopping.data.network.ShoppingItemService
import com.example.goshopping.domain.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ShoppingItemRepositoryImpl @Inject constructor(
    private val service: ShoppingItemService,
) : ShoppingItemRepository {
    // In-memory cache
    private val _shoppingListResultStream = MutableStateFlow<Result<List<ShoppingItem>>>(Result.success(emptyList()))

    override val shoppingListResultStream: Flow<Result<List<ShoppingItem>>> = _shoppingListResultStream.asStateFlow()

    override suspend fun fetchShoppingItems() {
        _shoppingListResultStream.value = Result.success(emptyList())

        _shoppingListResultStream.value = runCatching {
            val shoppingItemsResponse = service.getAllShoppingItems()

            if (shoppingItemsResponse.isSuccessful) {
                shoppingItemsResponse.body() ?: emptyList()
            } else {
                throw (Exception(shoppingItemsResponse.errorBody()?.string() ?: "Unknown error"))
            }
        }
    }

    override fun getShoppingItem(index: Int): ShoppingItem? {
        val cachedCountryListResult = _shoppingListResultStream.value
        return if (cachedCountryListResult.isSuccess) {
            val cachedCountries = cachedCountryListResult.getOrNull()
            cachedCountries?.getOrNull(index)
        } else { null }
    }

    override suspend fun markShoppingItemAsAddedToCart(shoppingItem: ShoppingItem) {
        _shoppingListResultStream.value.getOrNull()?.let {
            val shoppingItems = it.toMutableList()
            val shoppingItemIndex = _shoppingListResultStream.value.getOrNull()?.indexOf(shoppingItem) ?: -1
            if (shoppingItemIndex < 0) {
                return
            }

            val updatedShoppingItem = shoppingItem.copy(isAddedToCart = shoppingItem.isAddedToCart.not())
            shoppingItems[shoppingItemIndex] = updatedShoppingItem
            println(shoppingItemIndex)
            _shoppingListResultStream.value = Result.success(shoppingItems)
//            _shoppingListResultStream.value = Result.success(countryDao.getAllCountries())
        }
    }

}
