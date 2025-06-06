package com.example.goshopping.domain

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ShoppingItem(
    val title: String,
    val price: Double,
    val image: String,
    val category: String,
    val isAddedToCart: Boolean = false
) : Parcelable
