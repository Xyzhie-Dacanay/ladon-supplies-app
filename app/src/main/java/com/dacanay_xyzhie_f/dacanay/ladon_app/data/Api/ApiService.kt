package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.ApiResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.CartItemResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.LoginRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.LoginResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.ProductResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.FavoriteResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.OrderResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.StripeCheckoutResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.TicketRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.UpdateProfileRequest

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {
    @POST("/api/mobile/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
    @POST("/api/mobile/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @GET("/api/products")
    suspend fun getAllProducts(): List<ProductResponse>
    @GET("/api/products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<ProductResponse>

    @POST("api/mobile/update-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Response<Map<String, String>>
    @POST("api/mobile/tickets/submit")
    suspend fun submitTicket(
        @Header("Authorization") token: String,
        @Body request: TicketRequest
    ): Response<Map<String, String>>


    @GET("api/mobile/favorites")
    suspend fun getFavorites(@Header("Authorization") token: String): List<FavoriteResponse>

    @POST("api/mobile/favorites/add")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int>
    ): Response<Unit>

    @HTTP(method = "DELETE", path = "api/mobile/favorites/remove", hasBody = true)
    suspend fun removeFavorite(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int>
    ): Response<Unit>

    @GET("api/mobile/cart")
    suspend fun getCartItems(@Header("Authorization") token: String): List<CartItemResponse>

    @POST("api/mobile/cart")
    suspend fun addToCart(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int> // product_id, quantity
    ): Response<Unit>

    @POST("api/mobile/cart/update")
    suspend fun updateCartQuantity(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int> // ✅ Must be: product_id and quantity_change
    ): Response<Unit>

    @POST("api/mobile/cart/remove")
    suspend fun removeFromCart(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int> // ✅ Just product_id
    ): Response<Unit>

    @GET("api/mobile/address")
    suspend fun getAddresses(@Header("Authorization") token: String): List<AddressResponse>

    @POST("/api/mobile/address")
    suspend fun addAddress(
        @Header("Authorization") token: String,
        @Body request: AddressRequest
    ): Response<Map<String, String>>

    @HTTP(method = "DELETE", path = "api/mobile/address/delete", hasBody = true)
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int>
    ): Response<Unit>
    @POST("api/mobile/checkout")
    suspend fun mobileCheckout(
        @Header("Authorization") token: String,
        @Body body: Map<String, @JvmSuppressWildcards Any> // <-- this lets you mix String and List
    ): StripeCheckoutResponse
    @GET("api/mobile/orders")
    suspend fun getMobileOrders(@Header("Authorization") token: String): List<OrderResponse>
}


