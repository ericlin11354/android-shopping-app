package com.example.goshopping

import com.example.goshopping.domain.ShoppingItem

val shoppingList = listOf(
    ShoppingItem(
        title = "Apple",
        price = 2.99,
        image = "./assets/apple.jpg",
        category = "Fruits",
    ),
    ShoppingItem(
        title = "Car",
        price = 1000.99,
        image = "./assets/car.jpg",
        category = "Vehicle",
        isAddedToCart = false,
    ),
)