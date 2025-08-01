package com.example.authenticationapp.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.authenticationapp.home.HomeContent
import com.example.authenticationapp.login.LoginContent
import com.example.authenticationapp.service_locator.ServiceLocator
import com.example.authenticationapp.signup.SignupContent
import com.example.authenticationapp.ui.state.AuthenticationStateData

private data object LoginRoute
private data object PasswordRoute
private data object RegistrationRoute
private data object HomeRoute

@Composable
fun AuthenticationNavigation(
    uiState:  AuthenticationStateData,
) {
    // https://github.com/android/nav3-recipes/blob/main/app/src/main/java/com/example/nav3recipes/basic/BasicActivity.kt
    val entryRoute = if (uiState.token == null) LoginRoute else HomeRoute
    val backStack = remember { mutableStateListOf(entryRoute) }
    val serviceLocator = ServiceLocator(LocalContext.current)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is LoginRoute -> NavEntry(key) {
                    LoginContent(
                        serviceLocator = serviceLocator,
                        uiState = uiState,
                        navigateToSignup = { backStack.add(RegistrationRoute) },
                        completeAuthentication = {
                            serviceLocator.getAuthStorage().updateToken(it)
                            backStack.clear()
                            backStack.add(HomeRoute)
                        },
                    )
                }
                is RegistrationRoute -> NavEntry(key) {
                    SignupContent(
                        serviceLocator = serviceLocator,
                        uiState = uiState,
                        navigateToLogin = { backStack.add(LoginRoute) },
                        completeAuthentication = {
                            serviceLocator.getAuthStorage().updateToken(it)
                            backStack.clear()
                            backStack.add(HomeRoute)
                        },
                    )
                }
                is PasswordRoute -> NavEntry(key) {}
                is HomeRoute -> NavEntry(key) {
                    HomeContent(
                        revokeAuthentication = {
                            serviceLocator.getAuthStorage().deleteToken()
                            backStack.clear()
                            backStack.add(LoginRoute)
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