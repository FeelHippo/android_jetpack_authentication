package com.example.authenticationapp.data.network.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8
object RetrofitInstance {
    private const val BASE_URL = "https://e24a4994ed7f.ngrok-free.app/"

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}