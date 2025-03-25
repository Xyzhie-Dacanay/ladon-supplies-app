package com.dacanay_xyzhie_f.dacanay.ladon_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager

import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LogSignScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LoginScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.SignUpScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.favorites.FavoriteScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.HomeScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.ProductDetailsScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.ProductsScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.SeeAllScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.AddtoCartScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.CartItem
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.OrderScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.AboutScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.ProfileScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.HelpScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.PrivacyPolicvScreem
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.ReportScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.SettingsScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.EditProfile
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel


@Composable
fun AuthNavigation(navController: NavHostController, startDestination: String) {
    val cartList = remember { mutableStateListOf<CartItem>() }
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
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
            FavoriteScreen(navController = navController, viewModel = favoritesViewModel)
        }
        composable(Routes.Orders) {
            OrderScreen(navController = navController)
        }
        composable(Routes.Profile) {
            ProfileScreen(navController = navController, tokenManager = TokenManager(navController.context))
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
            ProductsScreen(navController = navController, category = category)
        }

        composable("product_details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            if (productId != null) {
                ProductDetailsScreen(navController, productId, cartList,  viewModel = favoritesViewModel)
            }
        }



        composable(Routes.AddtoCartScreen) {
            AddtoCartScreen(navController, cartList) }

        composable(Routes.EditProfileScreen) {
           EditProfile(navController = navController) }



        composable(Routes.SeeAllScreen) {
            SeeAllScreen(navController = navController)
        }





    }

















    }









