package com.dacanay_xyzhie_f.dacanay.ladon_app.data.repository

import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.ApiService
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressRequest

class AddressRepository(private val api: ApiService) {
    suspend fun addAddress(token: String, address: AddressRequest): Result<String> {
        return try {
            val response = api.addAddress("Bearer $token", address)
            if (response.isSuccessful) {
                Result.success(response.body()?.get("message") ?: "Address added!")
            } else {
                Result.failure(Exception("Failed to add address"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
