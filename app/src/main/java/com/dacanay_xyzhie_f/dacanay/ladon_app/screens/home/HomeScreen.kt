package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.ProductButtons
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.RatingCard

import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.productButtonList
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.AddtoCartScreen


@Composable
fun HomeScreen(navController: NavHostController) {
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
                        IconButton(onClick = { navController.navigate(Routes.AddtoCartScreen) }) {
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
                // Hero Image
                Image(
                    painter = painterResource(id = R.drawable.homepic),
                    contentDescription = "Home Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth()
                        .height(160.dp)

                )
            }

            item {
                // Categories Header
                Row(
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Categories",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Text Button for "See All"
                    TextButton(onClick = { navController.navigate(Routes.ProductsScreen) }) {
                        Text(
                            text = "See all",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF35AEFF)
                        )
                    }
                }
            }

            item {
                //  Product Buttons (Scrollable)
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(productButtonList) { product ->
                        ProductButtons(
                            imageResId = product.imageResId,
                            text = product.text,
                            onClick = { selectedCategory ->
                                navController.navigate("products/$selectedCategory")
                            })



                    }
                    }
            }

            item {

                // Ratings & Reviews Header
                Column(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
                    Text(
                        text = "Ratings & Reviews",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                // Product Cards (Scrollable)
                LazyRow(
                    contentPadding = PaddingValues(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productList) { product ->
                        RatingCard(
                            productName = product.name,
                            productPrice = product.price,
                            productImage = painterResource(id = product.imageRes),
                            rating = product.rating
                        )
                    }
                }
            }
        }
    }
}


        data class RatingProducts(

            val name: String,
            val price: String,
            val imageRes: Int,
            val rating: Float)

            val productList = listOf(
                RatingProducts("Short Bond Paper (1 Ream)", "P39.00", R.drawable.oneream, 1f),
                RatingProducts("1 pc Epson Ink", "P250.00", R.drawable.soloink, 4.5f),
                RatingProducts("Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen, 3f),
                RatingProducts("Colored Chalk (1 Box)", "P30.00", R.drawable.coloredchalk, 4.5f),
                RatingProducts("Chalk (1 Box)", "P30.00", R.drawable.chalkbox, 4.5f),




            )











