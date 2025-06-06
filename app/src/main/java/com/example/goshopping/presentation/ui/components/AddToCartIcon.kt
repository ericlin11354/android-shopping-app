package com.example.goshopping.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.goshopping.R
import com.example.goshopping.presentation.ui.theme.Yellow

@Composable
fun AddToCartIcon(isAddedToCart: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = {
            onClick()
        },
    ) {
        Icon(
            painter = painterResource(
                id = if (isAddedToCart) {
                    R.drawable.addtocart_filled
                } else {
                    R.drawable.addtocart_outline
                },
            ),
            contentDescription = stringResource(R.string.addtocart),
            modifier = Modifier
                .padding(all = 8.dp)
                .size(32.dp),
            tint = if (isAddedToCart) {
                Yellow
            } else {
                Color.Black
            }
        )
    }
}