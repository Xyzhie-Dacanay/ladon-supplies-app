package com.dacanay_xyzhie_f.dacanay.ladon_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.AuthNavigation
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LogSignScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.LadonappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

           val navController = rememberNavController()
            AuthNavigation(navController)

        }
    }
}

