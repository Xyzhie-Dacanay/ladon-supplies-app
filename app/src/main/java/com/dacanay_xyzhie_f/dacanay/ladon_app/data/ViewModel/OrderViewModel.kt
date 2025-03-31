package com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.OrderResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application.applicationContext)

    private val _orders = MutableStateFlow<List<OrderResponse>>(emptyList())
    val orders: StateFlow<List<OrderResponse>> = _orders

    fun fetchOrders() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token == null) {
                    Log.e("OrderViewModel", "Token is null")
                    return@launch
                }

                Log.d("OrderViewModel", "Using token: Bearer $token")
                val result = RetrofitInstance.api.getMobileOrders("Bearer $token")

                Log.d("OrderViewModel", "Fetched ${result.size} orders")
                result.forEachIndexed { index, order ->
                    Log.d("OrderViewModel", "Order #$index: $order")
                }

                _orders.value = result
            } catch (e: Exception) {
                Log.e("OrderViewModel", "Error fetching orders: ${e.message}", e)
            }
        }
    }
}
