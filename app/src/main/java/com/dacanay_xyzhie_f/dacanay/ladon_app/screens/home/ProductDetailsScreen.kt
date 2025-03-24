package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.ActualproductLists
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders.CartItem
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel

@Composable
fun ProductDetailsScreen(navController: NavController, productId: Int, cartList: MutableList<CartItem>,   viewModel: FavoritesViewModel) {
    val product = ActualproductLists.find { it.id == productId }
    val isFavorite = viewModel.favorites.collectAsState().value.contains(product)
    val context = LocalContext.current


    if (product == null) {
        Text(text = "Product not found", modifier = Modifier.padding(16.dp))
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Row {
                    IconButton(onClick = {
                        viewModel.toggleFavorite(product)
                        val message = if (viewModel.isFavorite(product)) {
                            "${product.name} added to favorites"
                        } else {
                            "${product.name} removed from favorites"
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.Red else Color.Black
                        )
                    }


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

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .size(480.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().height(480.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "₱${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Availability, Brand, Category
            ProductDetailRow(label = "Availability:", value = "In stock", isBold = true)
            ProductDetailRow(label = "Brand:", value = "Stabilo")
            ProductDetailRow(label = "Category:", value = product.category)

            Spacer(modifier = Modifier.weight(0.5f))

            Button(
                onClick = {
                    val selectedProduct = ActualproductLists.find { it.id == productId }

                    if (selectedProduct != null) {
                        val existingIndex = cartList.indexOfFirst { it.product.id == selectedProduct.id }

                        if (existingIndex != -1) {
                            val existingItem = cartList[existingIndex]
                            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
                            cartList[existingIndex] = updatedItem // ✅ replace with new object
                        } else {
                            cartList.add(CartItem(product = selectedProduct, quantity = 1))
                        }

                        Toast.makeText(context, "Added to Cart!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF35AEFF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


// Helper Composable
@Composable
fun ProductDetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}
