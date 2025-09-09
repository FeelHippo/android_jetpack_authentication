package com.example.authenticationapp.domain.models

data class UserModel(
    var id: String,
    var email: String,
    var username: String,
    var firstName: String,
    var lastName: String,
)