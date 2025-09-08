package com.example.authenticationapp.data.network.client

import com.example.authenticationapp.data.network.requests.LoginRequest
import com.example.authenticationapp.data.network.requests.RegistrationRequest
import com.example.authenticationapp.data.network.responses.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("register/")
    suspend fun postRegistration(@Body request: RegistrationRequest): Response<AuthenticationResponse>
    @POST("login/")
    suspend fun postLogin(@Body request: LoginRequest): Response<AuthenticationResponse>
    @GET("users/")
    suspend fun getUserById(@Query("id") id: String): Response<Any>
}