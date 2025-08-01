package com.example.authenticationapp.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.authenticationapp.service_locator.ServiceLocator
import com.example.authenticationapp.ui.state.AuthenticationStateData
import com.example.authenticationapp.ui.theme.AuthenticationAppTheme

@Preview
@Composable
fun LoginPreview() {
    AuthenticationAppTheme { LoginContent(
        serviceLocator = ServiceLocator(LocalContext.current),
        uiState = AuthenticationStateData("filippo@gmail.com", "123abc", "token"),
        navigateToSignup = {},
        completeAuthentication = {},
        modifier = Modifier,
    ) }
}