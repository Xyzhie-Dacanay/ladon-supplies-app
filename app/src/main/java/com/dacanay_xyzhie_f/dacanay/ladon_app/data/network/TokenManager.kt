package com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// ✅ DataStore Extension (must be outside class)
private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenManager(private val context: Context) {

    private val tokenKey = stringPreferencesKey("jwt_token")
    private val userIdKey = stringPreferencesKey("user_id")
    private val userNameKey = stringPreferencesKey("user_name")
    private val userImageKey = stringPreferencesKey("user_image_base64")


    suspend fun getToken(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[tokenKey] }
            .firstOrNull()
    }

    suspend fun getUserId(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[userIdKey] }
            .firstOrNull()
    }

    // ✅ Save all user data
    suspend fun saveUserData(
        token: String,
        userId: String,
        userName: String,
        profileImageBase64: String?
    ) {
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
            preferences[userIdKey] = userId
            preferences[userNameKey] = userName
            if (profileImageBase64 != null) {
                preferences[userImageKey] = profileImageBase64
            }
        }
    }

    // ✅ Save token only
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    // ✅ Flows for observing values
    val tokenFlow = context.dataStore.data.map { preferences -> preferences[tokenKey] }
    val userIdFlow = context.dataStore.data.map { preferences -> preferences[userIdKey] }
    val userNameFlow = context.dataStore.data.map { preferences -> preferences[userNameKey] }
    val userImageFlow = context.dataStore.data.map { preferences -> preferences[userImageKey] }

    // ✅ Clear all saved values
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
            preferences.remove(userIdKey)
            preferences.remove(userNameKey)
            preferences.remove(userImageKey)
        }
    }
}
