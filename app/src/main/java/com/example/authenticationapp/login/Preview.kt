package com.example.authenticationapp.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.authenticationapp.domain.models.UserModel
import com.example.authenticationapp.service_locator.ServiceLocator
import com.example.authenticationapp.ui.state.AuthenticationStateData
import com.example.authenticationapp.ui.theme.AuthenticationAppTheme

@Preview
@Composable
fun LoginPreview() {
    AuthenticationAppTheme { LoginContent(
        uiState = AuthenticationStateData(
            UserModel(
                "123abc",
                "filippo@email.com",
                "FeelHippo",
                "Filippo",
                "Miorin",
            ),
            "token"
        ),
        navigateToSignup = {},
        completeAuthentication = {} as @Composable ((String, String) -> Unit),
        modifier = Modifier,
    ) }
}