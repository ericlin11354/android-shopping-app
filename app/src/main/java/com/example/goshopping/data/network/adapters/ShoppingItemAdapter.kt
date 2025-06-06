package com.example.goshopping.data.network.adapters

import com.example.goshopping.data.network.dto.ShoppingItemDto
import com.example.goshopping.domain.ShoppingItem
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedShoppingList

class ShoppingItemAdapter {
    @WrappedShoppingList
    @FromJson
    fun fromJson(shoppingDtoList: List<ShoppingItemDto>): List<ShoppingItem> =
        shoppingDtoList.map { shoppingItemDto ->
            ShoppingItem(
                title = shoppingItemDto.title,
                price = shoppingItemDto.price,
                image = shoppingItemDto.image,
                category = shoppingItemDto.category
            )
        }

    @ToJson
    fun toJson(@WrappedShoppingList shoppingList: List<ShoppingItem>): List<ShoppingItemDto> =
        shoppingList.map { shoppingItem ->
            ShoppingItemDto(
                title = shoppingItem.title,
                price = shoppingItem.price,
                image = shoppingItem.image,
                category = shoppingItem.category
            )
        }
}