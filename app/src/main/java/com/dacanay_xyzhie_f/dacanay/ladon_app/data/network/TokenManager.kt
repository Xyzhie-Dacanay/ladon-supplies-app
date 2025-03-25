package com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// DataStore Extension
private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_IMAGE_KEY = stringPreferencesKey("user_image_base64")

        // Static token access
        suspend fun getToken(context: Context): String? {
            return context.dataStore.data
                .map { preferences -> preferences[TOKEN_KEY] }
                .firstOrNull()
        }
    }

    //  Save all user data (token, id, name, image)
    suspend fun saveUserData(token: String, userId: String, userName: String, profileImageBase64: String?) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_ID_KEY] = userId.toString()
            preferences[USER_NAME_KEY] = userName
            if (profileImageBase64 != null) {
                preferences[USER_IMAGE_KEY] = profileImageBase64
            }
        }
    }

    // Save token only (if needed)
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // Get token flow
    val tokenFlow = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    // Get user ID flow
    val userIdFlow = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }

    // Get user name flow
    val userNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }

    //  Get user image base64 flow
    val userImageFlow = context.dataStore.data.map { preferences ->
        preferences[USER_IMAGE_KEY]
    }

    // Clear all
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_IMAGE_KEY)
        }
    }
}
