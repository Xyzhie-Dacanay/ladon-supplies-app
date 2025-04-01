package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import ProductCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(navController: NavHostController) {
    val productViewModel: ProductViewModel = viewModel()
    val viewModel: FavoritesViewModel = viewModel()
    val allProducts by productViewModel.products
    var selectedFilter by remember { mutableStateOf("Default") }
    var expanded by remember { mutableStateOf(false) }

    val filteredProducts = remember(allProducts, selectedFilter) {
        when (selectedFilter) {
            "Price: Low to High" -> allProducts.sortedBy { it.product_price }
            "A-Z" -> allProducts.sortedBy { it.product_name }
            else -> allProducts
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Products",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(1f)
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
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter",
                                tint = Color.Black
                            )
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    productName = product.product_name,
                    productId = product.id,
                    productPrice = product.product_price.toString(),
                    productImage = product.product_image ?: "",
                    navController = navController,
                    isFavorite = false,
                    onFavoriteClick = { }
                )
            }
        }
    }
}

