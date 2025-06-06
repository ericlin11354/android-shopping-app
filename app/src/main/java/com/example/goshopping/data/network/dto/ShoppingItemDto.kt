package com.example.goshopping.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShoppingItemDto(
    val title: String,
    val price: Double,
    val image: String,
    val category: String
)