package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import ProductCard
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import base64ToImageBitmap
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.ProductButtons
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.ProductViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.productButtonList

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel = viewModel()) {
    val allProducts by viewModel.products
    val randomProducts = remember(allProducts) {
        allProducts.shuffled().take(6)
    }

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.primary),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Row {
                        IconButton(onClick = {
                            navController.navigate(Routes.AddtoCartScreen)
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(32.dp),
                                tint = Color.Black
                            )
                        }

                    }
                }
            }

            item {
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
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Categories",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
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
                            }
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All Products",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { navController.navigate(Routes.SeeAllScreen) }) {
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
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(randomProducts) { product ->
                        ProductCard(
                            productId = product.id,
                            productName = product.product_name,
                            productPrice = product.product_price.toString(),
                            productImage = product.product_image ?: "",
                            isFavorite = false,
                            onFavoriteClick = { },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
