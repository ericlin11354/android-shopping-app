package com.example.goshopping.data.network

import com.example.goshopping.data.network.adapters.WrappedShoppingList
import com.example.goshopping.domain.ShoppingItem
import retrofit2.Response
import retrofit2.http.GET

// Service gets all shopping item products
interface ShoppingItemService {
    @GET("products")
    @WrappedShoppingList
    suspend fun getAllShoppingItems(): Response<List<ShoppingItem>>
}