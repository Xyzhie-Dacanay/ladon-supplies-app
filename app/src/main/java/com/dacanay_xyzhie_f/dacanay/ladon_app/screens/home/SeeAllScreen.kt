package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home



import ProductCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.ActualproductLists
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel.FavoritesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(navController: NavHostController) {
    val viewModel: FavoritesViewModel = viewModel()
    var selectedFilter by remember { mutableStateOf("Default") }
    var expanded by remember { mutableStateOf(false) }

    val filteredProducts = remember(selectedFilter) {
        when (selectedFilter) {
            "Price: Low to High" -> ActualproductLists.sortedBy { it.price }
            "A-Z" -> ActualproductLists.sortedBy { it.name }
            else -> ActualproductLists
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Products",
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
                    // Icon filter button with dropdown
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.FilterList, // Or use Icons.Default.FilterList if preferred
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
                    productName = product.name,
                    productId = product.id,
                    productPrice = product.price.toString(),
                    productImage = product.imageRes,
                    navController = navController,
                    isFavorite = viewModel.isFavorite(product),
                    onFavoriteClick = { viewModel.toggleFavorite(product) }
                )
            }
        }
    }
}
