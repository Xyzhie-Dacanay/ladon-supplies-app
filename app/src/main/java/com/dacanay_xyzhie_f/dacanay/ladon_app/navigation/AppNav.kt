package com.dacanay_xyzhie_f.dacanay.ladon_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LogSignScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LoginScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.SignUpScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.favorites.FavoriteScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.HomeScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.ProductsScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.OrderScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.AboutScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.ProfileScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.HelpScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.PrivacyPolicvScreem
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.ReportScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.SettingsScreen

@Composable
fun AuthNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.LogSign) {
        composable(Routes.LogSign) {
            LogSignScreen(navController = navController)
    }
        composable(Routes.LogIn) {
            LoginScreen(navController = navController)
        }

        composable(Routes.SignUp) {
            SignUpScreen(navController = navController)
        }
        composable(Routes.HomePage) {
            HomeScreen(navController = navController)
        }
        composable(Routes.Favorites) {
            FavoriteScreen(navController = navController)
        }
        composable(Routes.Orders) {
            OrderScreen(navController = navController)
        }
        composable(Routes.Profile) {
            ProfileScreen(navController = navController)
        }

        composable(Routes.HelpCenter) {
            HelpScreen(navController = navController)
        }
        composable(Routes.About) {
            AboutScreen(navController = navController)
        }

        composable(Routes.PrivacyPolicy) {
            PrivacyPolicvScreem(navController = navController)
        }

        composable(Routes.Report) {
           ReportScreen(navController = navController)
        }

        composable(Routes.Settings) {
            SettingsScreen(navController = navController)
        }

        composable(Routes.ProductsScreen) {
            ProductsScreen(navController = navController)
        }

        composable("products/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "All"
            ProductsScreen(navController, category)
        }



    }

















    }









