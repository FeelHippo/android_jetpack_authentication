package com.example.authenticationapp.domain

import android.accounts.NetworkErrorException
import android.content.Context
import android.widget.Toast
import com.example.authenticationapp.data.network.requests.LoginRequest
import com.example.authenticationapp.data.network.requests.RegistrationRequest
import com.example.authenticationapp.domain.models.UserModel
import com.example.authenticationapp.service_locator.ServiceLocator

class AuthenticationRepository(private val context: Context) {
    private var serviceLocator = ServiceLocator(context)

    val isThereAUser get() = serviceLocator.getAuthStorage().isThereAUser
    val token get() = serviceLocator.getAuthStorage().currentToken
    val userUid get() = serviceLocator.getAuthStorage().currentUserUid

    suspend fun registration(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        toast: Toast,
        successCallback: (UserModel) -> Unit,
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
            serviceLocator.getAuthStorage().updateUserUid(
                response.body()!!.id
            )
            successCallback.invoke(
                UserModel(
                    id = response.body()!!.id,
                    email = response.body()!!.email,
                    username = response.body()!!.username,
                    firstName = response.body()!!.firstName,
                    lastName = response.body()!!.lastName,
                )
            )
        } catch (e: Error) {
            toast.show()
        }
    }

    suspend fun login(
        email: String,
        password: String,
        toast: Toast,
        successCallback: (UserModel) -> Unit,
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
            serviceLocator.getAuthStorage().updateUserUid(
                response.body()!!.id
            )
            successCallback.invoke(
                UserModel(
                    id = response.body()!!.id,
                    email = response.body()!!.email,
                    username = response.body()!!.username,
                    firstName = response.body()!!.firstName,
                    lastName = response.body()!!.lastName,
                )
            )
        } catch (e: Error) {
            toast.show()
        }
    }

    suspend fun getUserData(userUid: String): UserModel? {
        try {
            val response = serviceLocator.getAuthApiInterface().getUserById(userUid)
            if (!response.isSuccessful) {
                throw Error()
            }
            val userDataResponse = response.body()!!.user
            return UserModel(
                userUid,
                userDataResponse.email,
                userDataResponse.username,
                userDataResponse.firstName,
                userDataResponse.lastName
            )
        } catch (e: Error) {
            return null
        }
    }

    fun logout(
        successCallback: () -> Unit,
    ) {
        serviceLocator.getAuthStorage().deleteToken()
        serviceLocator.getAuthStorage().deleteUserUid()
        successCallback.invoke()
    }
}