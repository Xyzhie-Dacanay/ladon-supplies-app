package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class LoginResponse(
    val message:String,
    val token:String?,
    val user:User,
)
