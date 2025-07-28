package com.example.authenticationapp.data.network.requests


data class RegistrationRequest(
    var fullName: String,
    var email: String,
    var password: String,
)