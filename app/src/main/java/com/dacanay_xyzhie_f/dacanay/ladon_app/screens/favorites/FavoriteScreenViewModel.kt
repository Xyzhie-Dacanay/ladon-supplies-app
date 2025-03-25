package com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel

import androidx.lifecycle.ViewModel

import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites


    fun toggleFavorite(product: Product) {
        _favorites.value = if (_favorites.value.contains(product)) {
            _favorites.value - product
        } else {
            _favorites.value + product
        }
    }

    fun isFavorite(product: Product): Boolean {
        return _favorites.value.contains(product)
    }
}
