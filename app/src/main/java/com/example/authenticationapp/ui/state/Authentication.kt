package com.example.authenticationapp.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.authenticationapp.service_locator.ServiceLocator

@Composable
fun AuthenticationState(
    navigator: @Composable (AuthenticationStateData) -> Unit,
) {
    val context = LocalContext.current
    val storage = ServiceLocator(context).getAuthStorage()

    var email: String? = null
    var username: String? = null
    var firstname: String? = null
    var lastName: String? = null
    // make http call to fetch openID data here and store email and fullName

    // https://developer.android.com/develop/ui/compose/state#mapsaver
    var state by rememberSaveable(stateSaver = AuthenticationStateDataSaver) {
        mutableStateOf(
            AuthenticationStateData(
                email,
                username,
                firstname,
                lastName,
                storage.token,
            )
        )
    }
    navigator(state)
}

data class AuthenticationStateData(
    var email: String?,
    var username: String?,
    var firstName: String?,
    var lastName: String?,
    var token: String?
)

val AuthenticationStateDataSaver = run {
    val emailKey = "Email"
    val usernameKey = "UserName"
    val firstNameKey = "FirstName"
    val lastNameKey = "LastName"
    val tokenKey = "Token"
    mapSaver(
        save = { mapOf(
            emailKey to it.email,
            usernameKey to it.username,
            firstNameKey to it.firstName,
            lastNameKey to it.lastName,
            tokenKey to it.token,
        ) },
        restore = {
            AuthenticationStateData(
                it[emailKey] as String,
                it[usernameKey] as String,
                it[firstNameKey] as String,
                it[lastNameKey] as String,
                it[tokenKey] as String
            )
        }
    )
}