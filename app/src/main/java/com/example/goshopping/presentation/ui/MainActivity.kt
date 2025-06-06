package com.example.goshopping.presentation.ui

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.goshopping.data.prefs.GoShoppingPrefs
import com.example.goshopping.presentation.ui.nav.AppNavHost
import com.example.goshopping.presentation.ui.theme.GoShoppingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var prefs: GoShoppingPrefs

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch {
            prefs.rotationEnabledStream
                .collect { rotationEnabled ->
                    requestedOrientation = if (rotationEnabled) {
                        SCREEN_ORIENTATION_SENSOR
                    } else {
                        SCREEN_ORIENTATION_PORTRAIT
                    }
                }
        }

        enableEdgeToEdge()
        setContent {
            GoShoppingTheme {
                AppNavHost()
            }
        }
    }
}
