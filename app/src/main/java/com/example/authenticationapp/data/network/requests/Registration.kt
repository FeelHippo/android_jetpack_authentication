package com.example.authenticationapp.data.network.requests

import com.google.gson.annotations.SerializedName


data class RegistrationRequest(
    var email: String,
    var password: String,
    var username: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("is_admin")
    var isAdmin: Boolean? = false,
)