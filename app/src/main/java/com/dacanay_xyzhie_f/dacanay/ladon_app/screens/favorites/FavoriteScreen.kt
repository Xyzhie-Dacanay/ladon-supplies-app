package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.favorites

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.HomeButtonComponent
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.LoginButtonComponent
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.HomeScreen


@Composable
fun FavoriteScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.primary),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(100.dp)
                    )

                    Row {
                        IconButton(onClick = { /* Navigate to Cart */ }) {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(32.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        IconButton(onClick = { /* Navigate to Notifications */ }) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier.size(32.dp),
                                tint = Color.Black
                            )
                        }
                    }
                }
            }


item {
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.favs),
            contentDescription = "No Favorites",
            modifier = Modifier.size(200.dp)


        )

        Spacer(modifier = Modifier.height(16.dp))



        Text(
            text = "Your favorites is currently empty",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Start adding your favorite products!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray

            )

        Spacer(modifier = Modifier.height(16.dp))


        HomeButtonComponent(value = stringResource(id = R.string.homepage),
            navController = navController)
    }
}

        }
    }
}