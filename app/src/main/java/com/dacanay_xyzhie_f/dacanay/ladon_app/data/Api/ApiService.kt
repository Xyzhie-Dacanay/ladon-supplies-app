package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.LoginRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.LoginResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


interface ApiService {
    @POST("/api/mobile/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
    @POST("api/mobile/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}


