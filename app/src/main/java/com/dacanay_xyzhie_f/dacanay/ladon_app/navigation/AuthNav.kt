package com.dacanay_xyzhie_f.dacanay.ladon_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LogSignScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.LoginScreen
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth.SignUpScreen

@Composable
fun AuthNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.LogSign) {
        composable(Routes.LogSign) {
            LogSignScreen(navController)
    }
        composable(Routes.LogIn) {
            LoginScreen(navController)
        }

        composable(Routes.SignUp) {
            SignUpScreen(navController)
        }
        composable(Routes.HomePage) {
            SignUpScreen(navController)
        }


}









}