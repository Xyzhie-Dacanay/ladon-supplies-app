package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

import android.R

data class UpdateProfileRequest(
    val name: String,
    val fullname: String,
    val email: String,
    val contact: String,
    val profile_image: String? = null // base64 string
)