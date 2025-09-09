package com.example.authenticationapp.data.storage.secure

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthenticationStorage(preferences: SharedPreferences) {
    val sharedPreferences: SharedPreferences = preferences
    private val tokenName = "authentication_token"
    private val userUidName = "user_uid"
    private var token: String? = null
    private var userUid: String? = null

    val isThereAUser get() = this.token != null && this.userUid != null
    val currentToken get() = this.token
    val currentUserUid get() = this.userUid

    fun updateToken(newToken: String) {
        token = newToken
        sharedPreferences.edit {
            putString(tokenName, token)
        }
    }

    fun updateUserUid(newUid: String) {
        userUid = newUid
        sharedPreferences.edit {
            putString(userUidName, userUid)
        }
    }

    fun deleteToken() {
        token = null
        sharedPreferences.edit {
            remove(tokenName)
        }
    }

    fun deleteUserUid() {
        userUid = null
        sharedPreferences.edit {
            remove(userUidName)
        }
    }

    init {
        val existingToken = sharedPreferences.getString(tokenName, null)
        if (existingToken != null) {
            token = existingToken
        }
        val existingUserUid = sharedPreferences.getString(userUidName, null)
        if (existingUserUid != null) {
            userUid = existingUserUid
        }
    }

}