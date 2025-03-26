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
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.ActualproductLists
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavHostController, category: String) {
    val viewModel: FavoritesViewModel = viewModel()

    var selectedFilter by remember { mutableStateOf("Default") }
    var expanded by remember { mutableStateOf(false) }

    val baseFiltered = remember(category) {
        ActualproductLists.filter { it.name.contains(category, ignoreCase = true) }
    }

    val filteredProducts = remember(selectedFilter, baseFiltered) {
        when (selectedFilter) {
            "Price: Low to High" -> baseFiltered.sortedBy { it.price }
            "A-Z" -> baseFiltered.sortedBy { it.name }
            else -> baseFiltered
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
                            Icon(Icons.Default.FilterList,
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE6F8FF)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(paddingValues)
                .padding(horizontal = 4.dp)
        ) {
            item {
                Text(
                    text = "Products for $category",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            itemsIndexed(filteredProducts.chunked(2)) { _, rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    for (product in rowItems) {
                        ProductCard(
                            productName = product.name,
                            productId = product.id,
                            productPrice = product.price.toString(),
                            productImage = product.imageRes,
                            navController = navController,
                            isFavorite = viewModel.isFavorite(product),
                            onFavoriteClick = { viewModel.toggleFavorite(product) }
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
