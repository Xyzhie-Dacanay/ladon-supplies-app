package com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils

object InputValidator {

    // email validation for sign up
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // password validation for sign up
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    fun isUsernameValid(username: String): Boolean {
        return username.isNotEmpty() && username.length >= 3
    }


}
