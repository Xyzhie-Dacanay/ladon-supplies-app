package com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils

object InputValidator {
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    fun isUsernameValid(username: String): Boolean {
        return username.isNotEmpty() && username.length >= 3
    }
}