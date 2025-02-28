package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes

@Composable
fun NavBar(navController: NavController) {
    val navItemList = listOf(
        NavbarItem("Home", Icons.Default.Home, Routes.HomePage),
        NavbarItem("Favorites", Icons.Default.FavoriteBorder, Routes.Favorites),
        NavbarItem("Orders", Icons.Default.ShoppingBag, Routes.Orders),
        NavbarItem("Profile", Icons.Default.PersonOutline, Routes.Profile)
    )

    val currentRoute = navController.currentDestination?.route
    val primaryColor = Color(0xFF35AEFF) // ✅ Your primary color

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp)) // ✅ Rounded container
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {
            navItemList.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    if (isSelected) primaryColor else Color.Transparent, // ✅ Only primary color appears
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (isSelected) Color.White else Color.Gray // ✅ White icon when selected, gray when not
                            )
                        }
                    },
                    label = { Text(item.label, color = if (isSelected) primaryColor else Color.Gray) },
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Transparent, // ✅ Removes the default ripple background
                        unselectedIconColor = Color.Transparent,
                        selectedTextColor = primaryColor,
                        indicatorColor = Color.Transparent // ✅ Prevents extra color overlay
                    )
                )
            }
        }
    }
}
