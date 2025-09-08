package com.example.authenticationapp.service_locator

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.authenticationapp.data.network.client.ApiInterface
import com.example.authenticationapp.data.network.client.RetrofitInstance
import com.example.authenticationapp.data.storage.secure.AuthenticationStorage

// https://developer.android.com/training/dependency-injection#di-alternatives
class ServiceLocator(private val context: Context) {
    private lateinit var apiInterface: ApiInterface
    private lateinit var preferences: SharedPreferences

    private lateinit var storage: AuthenticationStorage

    fun getAuthApiInterface(): ApiInterface {
        if (!::apiInterface.isInitialized) {
            apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        }
        return apiInterface
    }

    fun getAuthPreferences(): SharedPreferences {
        if (!::preferences.isInitialized) {
            preferences = context.getSharedPreferences("auth_token", 0)
        }
        return preferences
    }

    fun getAuthStorage(): AuthenticationStorage {
        if (!::storage.isInitialized) {
            storage = AuthenticationStorage(getAuthPreferences())
        }
        return storage
    }

}