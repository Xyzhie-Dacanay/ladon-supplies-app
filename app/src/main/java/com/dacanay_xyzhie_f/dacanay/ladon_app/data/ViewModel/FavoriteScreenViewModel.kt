package com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodel

import FavoritesRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.FavoriteResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val tokenManager = TokenManager(context)
    private val repository = FavoritesRepository(tokenManager)

    private val _favorites = MutableStateFlow<List<FavoriteResponse>>(emptyList())
    val favorites: StateFlow<List<FavoriteResponse>> = _favorites

    // ✅ Load favorites from API
    fun fetchFavorites() {
        viewModelScope.launch {
            try {
                _favorites.value = repository.getFavorites()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Add a product to favorites
    fun addToFavorites(productId: Int) {
        viewModelScope.launch {
            try {
                repository.addFavorite(productId)
                fetchFavorites()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Remove a product from favorites
    fun removeFromFavorites(productId: Int) {
        viewModelScope.launch {
            try {
                repository.removeFavorite(productId)
                fetchFavorites()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Check if product is in favorites
    fun isFavorite(productId: Int): Boolean {
        return _favorites.value.any { it.product_id == productId }
    }
}
