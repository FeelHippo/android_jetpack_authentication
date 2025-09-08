package com.example.authenticationapp.domain

import android.accounts.NetworkErrorException
import android.content.Context
import android.widget.Toast
import com.example.authenticationapp.data.network.requests.LoginRequest
import com.example.authenticationapp.data.network.requests.RegistrationRequest
import com.example.authenticationapp.service_locator.ServiceLocator

class AuthenticationRepository(private val context: Context) {
    private var serviceLocator = ServiceLocator(context)

    suspend fun registration(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        toast: Toast,
        successCallback: () -> Unit,
    ) {
        try {
            val response = serviceLocator.getAuthApiInterface().postRegistration(
                request = RegistrationRequest(
                    email = email,
                    password = password,
                    username = username,
                    firstName = firstName,
                    lastName = lastName,
                )
            )
            if (!response.isSuccessful) {
                throw Error()
            }
            serviceLocator.getAuthStorage().updateToken(
                response.body()!!.token
            )
            successCallback.invoke()
        } catch (e: Error) {
            toast.show()
        }
    }

    suspend fun login(
        email: String,
        password: String,
        toast: Toast,
        successCallback: () -> Unit,
    ) {
        try {
            val response = serviceLocator.getAuthApiInterface().postLogin(
                request = LoginRequest(
                    email = email,
                    password = password,
                )
            )
            if (!response.isSuccessful) {
                throw Error()
            }
            serviceLocator.getAuthStorage().updateToken(
                response.body()!!.token
            )
            successCallback.invoke()
        } catch (e: Error) {
            toast.show()
        }
    }

    fun logout(
        successCallback: () -> Unit,
    ) {
        serviceLocator.getAuthStorage().deleteToken()
        successCallback.invoke()
    }
}