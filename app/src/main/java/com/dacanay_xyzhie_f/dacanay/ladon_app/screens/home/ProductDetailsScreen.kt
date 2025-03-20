package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.productLists

@Composable
fun ProductDetailsScreen(navController: NavController, productId: Int) {
    val product = productLists.find { it.id == productId } // ✅ Find product by ID

    if (product == null) {
        Text(text = "Product not found")
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text(text = "Price: ${product.price}", fontSize = 18.sp, color = Color.Gray)
            AsyncImage(
                model = product.imageRes,
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("checkout/${product.id}") }) {
                Text("Proceed to Checkout")
            }
        }
    }
}
