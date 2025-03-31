package com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.repository.AddressRepository
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AddressViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application.applicationContext)
    private val addressRepository = AddressRepository(RetrofitInstance.api)

    private val _addresses = MutableStateFlow<List<AddressResponse>>(emptyList())
    val addresses: StateFlow<List<AddressResponse>> = _addresses

    fun fetchAddresses() {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            try {
                val result = RetrofitInstance.api.getAddresses("Bearer $token")
                _addresses.value = result
            } catch (e: Exception) {
                Log.e("AddressViewModel", "Failed to fetch addresses: ${e.message}")
            }
        }
    }

    fun addAddress(address: AddressRequest, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            val result = addressRepository.addAddress(token, address)
            result.fold(
                onSuccess = {
                    onResult(true, it)
                    fetchAddresses()
                },
                onFailure = {
                    onResult(false, it.message ?: "Error")
                }
            )
        }
    }

    fun deleteAddress(addressId: Int) {
        viewModelScope.launch {
            val token = tokenManager.getToken() ?: return@launch
            try {
                val res = RetrofitInstance.api.deleteAddress("Bearer $token", mapOf("address_id" to addressId))
                if (res.isSuccessful) fetchAddresses()
            } catch (e: Exception) {
                Log.e("AddressViewModel", "Failed to delete address: ${e.message}")
            }
        }
    }
}
