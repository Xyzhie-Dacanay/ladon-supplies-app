package com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils.InputValidator
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.LoginRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.RegisterRequest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import android.util.Log
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.UpdateProfileRequest
import com.google.gson.Gson

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
    var loginResult by mutableStateOf<String?>(null)

    var isLoggedIn by mutableStateOf(false)
    var loggedInUserName by mutableStateOf("Guest")
    var loggedInUserId by mutableStateOf("")
    var profileImageBase64 by mutableStateOf<String?>(null)

    fun validateSignUp(): Boolean {
        usernameError = if (InputValidator.isUsernameValid(username)) null else "Username must be at least 3 characters"
        emailError = if (InputValidator.isEmailValid(email)) null else "Invalid email format"
        passwordError = if (InputValidator.isPasswordValid(password)) null else "Password must be at least 8 characters"
        confirmPasswordError = if (password == confirmPassword) null else "Passwords do not match"
        contactNumberError = if (contactNumber.length == 11 && contactNumber.all { it.isDigit() }) null else "Phone number must be 11 digits"

        return listOf(usernameError, emailError, passwordError, confirmPasswordError, contactNumberError).all { it == null }
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

    fun loginUser(
        tokenManager: TokenManager,
        onSuccess: (String?) -> Unit,
        onError: (String) -> Unit
    ) {
        signInEmailError = if (InputValidator.isEmailValid(email)) null else "Invalid email format"
        signInPasswordError = if (password.length >= 8) null else "Password must be at least 8 characters"

        if (signInEmailError != null || signInPasswordError != null) {
            onError("Please Input The Missing Fields")
            return
        }

        viewModelScope.launch {
            try {
                val request = LoginRequest(email = email, password = password)
                val response = RetrofitInstance.api.login(request)

                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()
                    val user = loginResponse?.user
                    val token = loginResponse?.token
                    val userId = user?.id?.toString() ?: ""
                    val name = user?.name ?: "User"
                    val profileImage = user?.profile_image

                    Log.d("LOGIN", "User Logged In: $name ($userId)")

                    token?.let {
                        tokenManager.saveUserData(it, userId, name, profileImage)
                        isLoggedIn = true
                        loggedInUserId = userId
                        loggedInUserName = name
                        profileImageBase64 = profileImage
                        loginResult = "Login successful"
                        onSuccess(it)
                    } ?: run {
                        onError("Token missing in response.")
                    }
                } else {
                    onError("Login failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                onError("Error: ${e.localizedMessage}")
            }
        }
    }

    fun logout(tokenManager: TokenManager, onComplete: () -> Unit) {
        viewModelScope.launch {
            tokenManager.clearToken()
            isLoggedIn = false
            loggedInUserName = "Guest"
            loggedInUserId = ""
            profileImageBase64 = null
            onComplete()
        }
    }

    fun loadUserFromToken(tokenManager: TokenManager) {
        viewModelScope.launch {
            val name = tokenManager.userNameFlow.firstOrNull()
            val id = tokenManager.userIdFlow.firstOrNull()
            val token = tokenManager.tokenFlow.firstOrNull()
            val image = tokenManager.userImageFlow.firstOrNull()

            Log.d("TOKEN_LOAD", "name=$name, id=$id, token=$token")

            if (!token.isNullOrEmpty()) {
                isLoggedIn = true
                loggedInUserName = name?.takeIf { it.isNotBlank() } ?: "Guest"
                loggedInUserId = id ?: ""
                profileImageBase64 = image
            }
        }
    }
    fun updateUserProfile(
        tokenManager: TokenManager,
        request: UpdateProfileRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token == null) {
                    onError("Token not found.")
                    return@launch
                }
                Log.d("PROFILE_UPDATE", "Sending: ${Gson().toJson(request)}")
                val response = RetrofitInstance.api.updateProfile("Bearer $token", request)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                onError("Exception: ${e.localizedMessage}")
            }
        }
    }
}
