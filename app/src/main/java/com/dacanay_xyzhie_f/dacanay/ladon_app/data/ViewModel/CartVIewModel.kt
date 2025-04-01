package com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.CartItemResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenManager = TokenManager(application.applicationContext)

    private val _cartItems = MutableStateFlow<List<CartItemResponse>>(emptyList())
    val cartItems: StateFlow<List<CartItemResponse>> = _cartItems

    fun fetchCartItems() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken() ?: return@launch
                val items = RetrofitInstance.api.getCartItems("Bearer $token")
                _cartItems.value = items
            } catch (e: Exception) {
                Log.e("CartViewModel", "Failed to fetch cart items: ${e.message}")
            }
        }
    }

    fun updateCartQuantity(productId: Int, quantityChange: Int) {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            try {
                val response = RetrofitInstance.api.updateCartQuantity(
                    token = "Bearer $token",
                    body = mapOf(
                        "product_id" to productId,
                        "quantity_change" to quantityChange
                    )
                )
                if (response.isSuccessful) {
                    fetchCartItems()
                    Log.d("CartViewModel", "Quantity updated successfully")
                } else {
                    Log.e("CartViewModel", "Failed to update quantity: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error updating quantity: ${e.message}")
            }
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            try {
                val response = RetrofitInstance.api.addToCart(
                    token = "Bearer $token",
                    body = mapOf(
                        "product_id" to productId,
                        "quantity" to quantity
                    )
                )
                if (response.isSuccessful) {
                    fetchCartItems()
                    Log.d("CartViewModel", "Item added to cart successfully")
                } else {
                    Log.e("CartViewModel", "Failed to add to cart: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Exception during addToCart: ${e.message}")
            }
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            try {
                val response = RetrofitInstance.api.removeFromCart(
                    token = "Bearer $token",
                    body = mapOf("product_id" to productId)
                )
                if (response.isSuccessful) {
                    fetchCartItems()
                    Log.d("CartViewModel", "Item removed from cart")
                } else {
                    Log.e("CartViewModel", "Failed to remove from cart: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Exception during removeFromCart: ${e.message}")
            }
        }
    }

    suspend fun getStripeCheckoutUrl(address: String, selectedItems: List<CartItemResponse>): String? {
        return try {
            val token = tokenManager.getToken()
            if (token == null) {
                Log.e("CartViewModel", "Token is null")
                return null
            }

            val checkoutBody = mapOf(
                "address_id" to address,
                "items" to selectedItems.map {
                    mapOf(
                        "product_id" to it.product_id,
                        "quantity" to it.quantity
                    )
                }
            )

            val response = RetrofitInstance.api.mobileCheckout(
                token = "Bearer $token",
                body = checkoutBody
            )

            Log.d("CartViewModel", "Checkout response: $response")
            Log.d("CartViewModel", "Checkout URL: ${response.checkout_url}")
            response.checkout_url
        } catch (e: Exception) {
            Log.e("CartViewModel", "Failed to get Stripe Checkout URL: ${e.message}", e)
            null
        }
    }
}
