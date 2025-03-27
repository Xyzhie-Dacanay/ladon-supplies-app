package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = mutableStateOf<List<ProductResponse>>(emptyList())
    val products: State<List<ProductResponse>> = _products

    // ✅ 👇 Define productsByCategory and fetchProductsByCategory here
    var productsByCategory = mutableStateOf<List<ProductResponse>>(emptyList())
        private set

    fun fetchProductsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductsByCategory(category)
                productsByCategory.value = response
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Failed to fetch products for $category", e)
            }
        }
    }

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllProducts()
                _products.value = response
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products: ${e.message}")
            }
        }
    }
}
