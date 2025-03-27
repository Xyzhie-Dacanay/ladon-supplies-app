package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import ProductCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavHostController, category: String) {
    val productViewModel: ProductViewModel = viewModel()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    var selectedFilter by remember { mutableStateOf("Default") }
    var expanded by remember { mutableStateOf(false) }

    // 👇 Fetch products by category when screen loads
    LaunchedEffect(category) {
        productViewModel.fetchProductsByCategory(category)
    }

    val productsByCategory by productViewModel.productsByCategory

    val sortedProducts = remember(productsByCategory, selectedFilter) {
        when (selectedFilter) {
            "Price: Low to High" -> productsByCategory.sortedBy { it.product_price }
            "A-Z" -> productsByCategory.sortedBy { it.product_name }
            else -> productsByCategory
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.FilterList, contentDescription = "Filter")
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Default") },
                                onClick = {
                                    selectedFilter = "Default"
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Price: Low to High") },
                                onClick = {
                                    selectedFilter = "Price: Low to High"
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("A-Z") },
                                onClick = {
                                    selectedFilter = "A-Z"
                                    expanded = false
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE6F8FF))
            )
        },
        containerColor = Color(0xFFE6F8FF)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            item {
                Text(
                    text = "Products for \"$category\"",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (sortedProducts.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No products found for \"$category\".",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                itemsIndexed(sortedProducts.chunked(2)) { _, rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { product ->
                            ProductCard(
                                productId = product.id,
                                productName = product.product_name,
                                productPrice = product.product_price.toString(),
                                productImage = product.product_image ?: "",
                                navController = navController,
                                isFavorite = false,
                                onFavoriteClick = { }
                            )
                        }

                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
