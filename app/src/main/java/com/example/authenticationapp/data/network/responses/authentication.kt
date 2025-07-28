package com.example.authenticationapp.data.network.responses


data class AuthenticationResponse(
    var isAuthenticated: Boolean = false,
    var token: String?,
    var message: String?,
)