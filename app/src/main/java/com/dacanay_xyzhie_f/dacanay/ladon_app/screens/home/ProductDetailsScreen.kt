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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import base64ToImageBitmap
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel = viewModel(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    val context = LocalContext.current
    val product = viewModel.products.value.find { it.id == productId }
    val favorites by favoritesViewModel.favorites.collectAsState()
    val isFavorite = favorites.any { it.product_id == productId }

    if (product == null) {
        Text("Product not found", modifier = Modifier.padding(16.dp))
        return
    }

    LaunchedEffect(Unit) {
        favoritesViewModel.fetchFavorites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F8FF))
            .padding(12.dp)
    ) {
        // Top Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }

            Row {
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            favoritesViewModel.removeFromFavorites(productId)
                            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                        } else {
                            favoritesViewModel.addToFavorites(productId)
                            Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Black
                    )
                }

                IconButton(onClick = { navController.navigate(Routes.AddtoCartScreen) }) {
                    Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = base64ToImageBitmap(context, product.product_image ?: ""),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Name & Price
        Text(product.product_name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("₱${product.product_price}", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(16.dp))

        ProductDetailRow("Availability", if ((product.stock ?: 0) > 0) "In stock" else "Out of stock", isBold = true)
        ProductDetailRow("Category", product.product_category ?: "Uncategorized")

        Spacer(modifier = Modifier.weight(1f))

        // Add to Cart Button
        Button(
            onClick = {
                Toast.makeText(context, "Added to Cart!", Toast.LENGTH_SHORT).show()
                // 💡 You can call your API here to add to cart
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Add to Cart", fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}


// Helper
@Composable
fun ProductDetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 16.sp, color = Color.Black)
        Text(value, fontSize = 16.sp, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
    }
}
