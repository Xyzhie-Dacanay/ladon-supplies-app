package com.dacanay_xyzhie_f.dacanay.ladon_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.AuthNavigation
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.LadonappTheme
import kotlinx.coroutines.flow.firstOrNull
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install Splash Screen FIRST
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LadonappTheme {
                val navController = rememberNavController()
                val context = applicationContext
                val authViewModel: AuthViewModel = viewModel()

                // ✅ Move isLoading inside setContent
                var isLoading by remember { mutableStateOf(true) }
                var startDestination by remember { mutableStateOf<String?>(null) }

                // ✅ Set splash screen condition here (INSIDE setContent)
                splashScreen.setKeepOnScreenCondition { isLoading }

                // Load user/token and set start destination
                LaunchedEffect(Unit) {
                    val tokenManager = TokenManager(context)
                    authViewModel.loadUserFromToken(tokenManager)
                    val token = tokenManager.tokenFlow.firstOrNull()
                    startDestination = if (token != null) Routes.HomePage else Routes.LogSign
                    isLoading = false // 🚨 triggers splash to dismiss
                }

                // Start nav only after destination is ready
                startDestination?.let { start ->
                    AuthNavigation(
                        navController = navController,
                        startDestination = start,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}

