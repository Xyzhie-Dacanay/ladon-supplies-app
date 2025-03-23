package com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils.InputValidator

class AuthViewModel : ViewModel() {


    var contactNumber by mutableStateOf("")
    var contactNumberError by mutableStateOf<String?>(null)

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")


    var usernameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)


    fun validateSignUp(): Boolean {
        usernameError = if (InputValidator.isUsernameValid(username)) null else "Username must be at least 3 characters"
        emailError = if (InputValidator.isEmailValid(email)) null else "Invalid email format"
        passwordError = if (InputValidator.isPasswordValid(password)) null else "Password must be at least 8 characters "
        confirmPasswordError = if (password == confirmPassword) null else "Passwords do not match"
        contactNumberError = if (contactNumber.length == 11 && contactNumber.all { it.isDigit() }) null else "Phone number must be 11 digits"

        return usernameError == null && emailError == null && passwordError == null && confirmPasswordError == null &&
                contactNumberError == null

    }
}
