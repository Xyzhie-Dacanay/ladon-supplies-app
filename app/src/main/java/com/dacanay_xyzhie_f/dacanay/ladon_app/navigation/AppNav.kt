package com.dacanay_xyzhie_f.dacanay.ladon_app.navigation

import EditProfile
import android.net.Uri
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.AddressViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.CartViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.ProductViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.WebView.CheckoutWebViewScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.favorites.FavoriteScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthNavigation(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel
) {

    val favoritesViewModel: FavoritesViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()
    var selectedAddress by remember { mutableStateOf("Default Address") }
    val savedAddresses = remember { mutableStateListOf<AddressEntry>() }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.LogSign) {
            LogSignScreen(navController = navController)
        }
        composable(Routes.LogIn) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(Routes.SignUp) {
            SignUpScreen(navController = navController, authViewModel = authViewModel)
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
            ProfileScreen(
                navController = navController,
                tokenManager = TokenManager(navController.context),
                authViewModel = authViewModel
            )
        }
        composable(Routes.HelpCenter) {
            HelpScreen(navController = navController)
        }
        composable(Routes.About) {
            AboutScreen(navController = navController)
        }
        composable(Routes.PrivacyPolicy) {
            PrivacyPolicyScreen(navController = navController)
        }
        composable(Routes.Report) {
            ReportScreen(navController = navController)
        }
        composable(Routes.ReportProblemScreen){
            ReportProblemScreen(navController = navController)
        }
        composable(Routes.Settings) {
            SettingsScreen(navController = navController)
        }
        composable(Routes.ProductsScreen) {
            ProductsScreen(navController = navController)
        }
        composable("products/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ProductsScreen(navController = navController, category = category)
        }

        composable("product_details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            if (productId != null) {
                ProductDetailsScreen(
                    navController = navController,
                    productId = productId,
                    favoritesViewModel = favoritesViewModel,
                    authViewModel = authViewModel
                )
            }
        }

        composable(Routes.AddtoCartScreen) {
            val cartViewModel: CartViewModel = viewModel()
            val addressViewModel: AddressViewModel = viewModel()

            AddtoCartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                addressViewModel = addressViewModel
            )
        }

        composable("webview_checkout?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")?.let { Uri.decode(it) }
            val cartViewModel: CartViewModel = viewModel()
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            if (url != null) {
                CheckoutWebViewScreen(url = url) {
                    navController.popBackStack()
                    scope.launch {
                        snackbarHostState.showSnackbar("Payment Successful!")
                    }
                    cartViewModel.fetchCartItems()
                }
            }
        }

        composable(Routes.EditProfileScreen) {
            EditProfile(navController = navController,
                authViewModel = authViewModel,
                tokenManager = TokenManager(navController.context))
        }

        composable(Routes.SeeAllScreen) {
            SeeAllScreen(navController = navController)
        }

        composable(Routes.AddNewAddressScreen) {
            val addressViewModel: AddressViewModel = viewModel()

            AddNewAddressScreen(
                navController = navController,
                addressViewModel = addressViewModel,
                onSaveAddress = { newAddress ->
                    savedAddresses.forEachIndexed { index, entry ->
                        savedAddresses[index] = entry.copy(isDefault = false)
                    }
                    val newEntry = AddressEntry(address = newAddress, isDefault = true)
                    savedAddresses.add(newEntry)
                    selectedAddress = newAddress
                }
            )
        }
    }
}
