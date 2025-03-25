package com.dacanay_xyzhie_f.dacanay.ladon_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.AuthNavigation
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.LadonappTheme
import kotlinx.coroutines.flow.firstOrNull

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LadonappTheme {
                val navController = rememberNavController()
                val context = applicationContext
                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    val tokenManager = TokenManager(context)
                    val token = tokenManager.tokenFlow.firstOrNull() // ✅ Use tokenFlow here
                    startDestination = if (token != null) Routes.HomePage else Routes.LogSign
                }

                startDestination?.let { start ->
                    AuthNavigation(navController = navController, startDestination = start)
                }
            }
        }
    }
}
