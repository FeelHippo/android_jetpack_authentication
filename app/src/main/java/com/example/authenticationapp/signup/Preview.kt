package com.example.authenticationapp.signup



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.authenticationapp.login.LoginContent
import com.example.authenticationapp.service_locator.ServiceLocator
import com.example.authenticationapp.ui.state.AuthenticationStateData
import com.example.authenticationapp.ui.theme.AuthenticationAppTheme

@Preview
@Composable
fun LoginPreview() {
    AuthenticationAppTheme { SignupContent(
        serviceLocator = ServiceLocator(LocalContext.current),
        uiState = AuthenticationStateData("filippo@gmail.com", "123abc", "token"),
        navigateToLogin = {},
        completeAuthentication = {},
        modifier = Modifier,
    ) }
}