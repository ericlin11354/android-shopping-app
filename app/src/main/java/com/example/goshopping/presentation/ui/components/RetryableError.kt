package com.zaclippard.androidworkshopapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.goshopping.R

@Composable
fun RetryableError(message: String, onRetry: () -> Unit) {
    Column(horizontalAlignment = Alignment.Companion.CenterHorizontally) {
        Text(message)
        Button(onRetry) {
            Text(stringResource(R.string.error_retry))
        }
    }
}
