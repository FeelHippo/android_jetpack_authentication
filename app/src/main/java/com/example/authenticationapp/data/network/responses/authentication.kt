package com.example.authenticationapp.data.network.responses

import com.google.gson.annotations.SerializedName


data class AuthenticationResponse(
    var token: String,
    var id: String,
    var email: String,
    var username: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("is_admin")
    var isAdmin: Boolean? = false,
)