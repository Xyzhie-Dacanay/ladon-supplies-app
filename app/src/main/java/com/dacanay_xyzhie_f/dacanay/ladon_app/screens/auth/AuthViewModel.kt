package com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils.InputValidator
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterRequest
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var signInEmailError by mutableStateOf<String?>(null)
    var signInPasswordError by mutableStateOf<String?>(null)


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

    var registerResult by mutableStateOf<String?>(null)

    fun validateSignUp(): Boolean {
        usernameError = if (InputValidator.isUsernameValid(username)) null else "Username must be at least 3 characters"
        emailError = if (InputValidator.isEmailValid(email)) null else "Invalid email format"
        passwordError = if (InputValidator.isPasswordValid(password)) null else "Password must be at least 8 characters"
        confirmPasswordError = if (password == confirmPassword) null else "Passwords do not match"
        contactNumberError = if (contactNumber.length == 11 && contactNumber.all { it.isDigit() }) null else "Phone number must be 11 digits"

        return listOf(
            usernameError, emailError, passwordError,
            confirmPasswordError, contactNumberError
        ).all { it == null }
    }

    fun validateSignIn(): Boolean {
        signInEmailError = when {
            email.isBlank() -> "Email cannot be empty"
            !email.contains("@") -> "Email must contain @"
            !email.contains(".com") -> "Email must contain .com"
            else -> null
        }

        signInPasswordError = when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 8 -> "Password must be at least 8 characters"
            else -> null
        }

        return signInEmailError == null && signInPasswordError == null
    }


    fun registerUser(onSuccess: () -> Unit) {
        if (!validateSignUp()) return

        viewModelScope.launch {
            try {
                val request = RegisterRequest(
                    name = username,
                    email = email,
                    password = password,
                    contact = contactNumber
                )

                val response = RetrofitInstance.api.register(request)

                if (response.isSuccessful) {
                    registerResult = response.body()?.message ?: "Registered successfully"

                    // ✅ Clear fields
                    username = ""
                    email = ""
                    password = ""
                    confirmPassword = ""
                    contactNumber = ""


                    onSuccess()
                } else {
                    registerResult = "Failed: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                registerResult = "Error: ${e.localizedMessage}"
            }
        }
    }
}
