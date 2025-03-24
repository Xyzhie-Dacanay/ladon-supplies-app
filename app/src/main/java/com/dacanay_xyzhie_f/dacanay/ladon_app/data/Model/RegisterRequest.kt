package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val contact: String,
    val level_type: String = "user"
)
