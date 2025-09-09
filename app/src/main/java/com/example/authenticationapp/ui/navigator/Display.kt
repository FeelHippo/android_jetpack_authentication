package com.example.authenticationapp.ui.navigator

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.authenticationapp.domain.AuthenticationRepository
import com.example.authenticationapp.domain.models.UserModel
import com.example.authenticationapp.home.HomeContent
import com.example.authenticationapp.login.LoginContent
import com.example.authenticationapp.service_locator.ServiceLocator
import com.example.authenticationapp.signup.SignupContent
import com.example.authenticationapp.ui.state.AuthenticationStateData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

private data object LoginRoute
private data object PasswordRoute
private data object RegistrationRoute
private data object HomeRoute

@Composable
fun AuthenticationNavigation(
    uiState:  AuthenticationStateData,
    updateState: (UserModel) -> Unit
) {
    // https://github.com/android/nav3-recipes/blob/main/app/src/main/java/com/example/nav3recipes/basic/BasicActivity.kt
    val entryRoute = if (uiState.token == null) LoginRoute else HomeRoute
    val backStack = remember { mutableStateListOf(entryRoute) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val repository = AuthenticationRepository(context)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is RegistrationRoute -> NavEntry(key) {
                    SignupContent(
                        uiState = uiState,
                        navigateToLogin = { backStack.add(LoginRoute) },
                        completeAuthentication = { email, password, username, firstName, lastName ->
                            LaunchedEffect(coroutineScope) {
                                repository.registration(
                                    email,
                                    password,
                                    username,
                                    firstName,
                                    lastName,
                                    Toast.makeText(
                                        context, "Could not create user :/",
                                        Toast.LENGTH_LONG
                                    ),
                                ) { userModel ->
                                    // update UI
                                    updateState(userModel)
                                    // update navigator
                                    backStack.clear()
                                    backStack.add(HomeRoute)
                                }
                            }
                        },
                    )
                }
                is LoginRoute -> NavEntry(key) {
                    LoginContent(
                        uiState = uiState,
                        navigateToSignup = { backStack.add(RegistrationRoute) },
                        completeAuthentication = { email, password ->
                            LaunchedEffect(coroutineScope) {
                                repository.login(
                                    email,
                                    password,
                                    Toast.makeText(context, "Could not create user :/", Toast.LENGTH_LONG),
                                ) { userModel ->
                                    // update UI
                                    updateState(userModel)
                                    // update navigator
                                    backStack.clear()
                                    backStack.add(HomeRoute)
                                }
                            }
                        },
                    )
                }
                is PasswordRoute -> NavEntry(key) {}
                is HomeRoute -> NavEntry(key) {
                    HomeContent(
                        uiState = uiState,
                        revokeAuthentication = {
                            repository.logout {
                                uiState.userModel = null
                                uiState.token = null
                                backStack.clear()
                                backStack.add(LoginRoute)
                            }
                        }
                    )
                }
                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}