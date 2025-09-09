package com.example.authenticationapp.data.network.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    var user: UserDataResponse,
)

data class UserDataResponse(
    var id: String,
    var email: String,
    var username: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
)