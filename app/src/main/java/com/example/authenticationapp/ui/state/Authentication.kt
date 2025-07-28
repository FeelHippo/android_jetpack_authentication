package com.example.authenticationapp.ui.state

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.authenticationapp.service_locator.ServiceLocator

@Composable
fun AuthenticationState(
    context: Context,
    navigator: @Composable (AuthenticationStateData) -> Unit,
) {
    val storage = ServiceLocator(context).getAuthStorage()

    var email: String? = null
    var fullName: String? = null
    // make http call to fetch openID data here and store email and fullName

    // https://developer.android.com/develop/ui/compose/state#mapsaver
    var state by rememberSaveable(stateSaver = AuthenticationStateDataSaver) {
        mutableStateOf(
            AuthenticationStateData(
                email,
                fullName,
                storage.token,
            )
        )
    }
    navigator(state)
}

data class AuthenticationStateData(
    var email: String?,
    var fullName: String?,
    var token: String?
)

val AuthenticationStateDataSaver = run {
    val emailKey = "Email"
    val fullNameKey = "FullName"
    val tokenKey = "Token"
    mapSaver(
        save = { mapOf(
            emailKey to it.email,
            fullNameKey to it.fullName,
            tokenKey to it.token,
        ) },
        restore = {
            AuthenticationStateData(
                it[emailKey] as String,
                it[fullNameKey] as String,
                it[tokenKey] as String
            )
        }
    )
}