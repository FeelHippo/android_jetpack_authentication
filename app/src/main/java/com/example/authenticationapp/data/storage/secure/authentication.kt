package com.example.authenticationapp.data.storage.secure

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthenticationStorage(preferences: SharedPreferences) {
    val sharedPreferences: SharedPreferences = preferences
    val tokenName = "authentication_token"
    var token: String? = null

    fun updateToken(newToken: String) {
        token = newToken
        sharedPreferences.edit {
            putString(tokenName, token)
        }
    }

    init {
        val existingToken = sharedPreferences.getString(tokenName, null)
        if (existingToken != null) {
            token = existingToken
        }
    }

}