package com.example.authenticationapp.data.network.client

import com.example.authenticationapp.data.network.requests.AuthenticationRequest
import com.example.authenticationapp.data.network.requests.RegistrationRequest
import com.example.authenticationapp.data.network.responses.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/auth")
    suspend fun postAuth(@Body request: AuthenticationRequest): Response<AuthenticationResponse>
    @POST("api/registration")
    suspend fun postRegistration(@Body request: RegistrationRequest): Response<AuthenticationResponse>
    @GET("api")
    suspend fun getYesOrNo(): Response<Any>
}