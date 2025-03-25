package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class User(
    val id: Int,
    val name: String,
    val fullname: String?,      // nullable
    val email: String,
    val password: String,
    val contact: String,
    val profile_image: String?, // nullable
    val level_type: String,
    val auth_token: String?,    // nullable
    val created_at: String
)
