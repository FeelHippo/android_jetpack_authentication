package com.example.authenticationapp.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.authenticationapp.domain.AuthenticationRepository
import com.example.authenticationapp.domain.models.UserModel

@Composable
fun AuthenticationState(
    navigator: @Composable (AuthenticationStateData, (UserModel) -> Unit) -> Unit,
) {
    val context = LocalContext.current
    val repository = AuthenticationRepository(context)

    // https://developer.android.com/develop/ui/compose/state#mapsaver
    var state by rememberSaveable(stateSaver = AuthenticationStateDataSaver) {
        mutableStateOf(
            AuthenticationStateData(
                null,
                repository.token,
            )
        )
    }

    if (repository.isThereAUser) {
        val coroutineScope = rememberCoroutineScope()
        LaunchedEffect(coroutineScope) {
            val existingUser = repository.getUserData(repository.userUid!!)
            if (existingUser != null) {
                state = state.copy(existingUser)
            }
        }
    }

    navigator(state) { userModel -> state = state.copy(userModel) }

}

data class AuthenticationStateData(
    var userModel: UserModel?,
    var token: String?
)

val AuthenticationStateDataSaver = run {
    val userKey = "User"
    val tokenKey = "Token"
    mapSaver(
        save = { mapOf(
            userKey to it.userModel,
            tokenKey to it.token,
        ) },
        restore = {
            AuthenticationStateData(
                it[userKey] as UserModel,
                it[tokenKey] as String
            )
        }
    )
}