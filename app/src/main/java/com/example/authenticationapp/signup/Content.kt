package com.example.authenticationapp.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.authenticationapp.R
import com.example.authenticationapp.components.InputField
import com.example.authenticationapp.ui.state.AuthenticationStateData

/**
 * Entry point for the authentication screen.
 *
 * @param uiState [AuthenticationStateData] that contains email authentication state
 * @param navigateToLogin [Unit] User action when navigation to login is requested
 * @param completeAuthentication [Unit] User action when navigation to home is requested
 * @param modifier [Modifier] to apply to this layout node
 *
 * https://www.figma.com/design/gnLdrXC66waMZG0CprzxCv/%E2%9C%A8-Login-and-Signup-Screen----harshadux--Community-?node-id=1-652&t=dhHlUDjFdeMa3fBw-0
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupContent(
    uiState: AuthenticationStateData,
    navigateToLogin: (String?) -> Unit,
    completeAuthentication: @Composable (String, String, String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val userModel = uiState.userModel

    var email by rememberSaveable { mutableStateOf(userModel?.email ?: "") }
    var username by rememberSaveable { mutableStateOf(userModel?.username ?: "") }
    var firstName by rememberSaveable { mutableStateOf(userModel?.firstName ?: "") }
    var lastName by rememberSaveable { mutableStateOf(userModel?.lastName ?: "") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }

    val title = stringResource(R.string.title_signup)
    val labelUserName = stringResource(R.string.label_user_name)
    val labelFirstName = stringResource(R.string.label_first_name)
    val labelLastName = stringResource(R.string.label_last_name)
    val labelEmail = stringResource(R.string.label_email)
    val labelPassword = stringResource(R.string.label_password)
    val labelPasswordRepeat = stringResource(R.string.label_password_repeat)
    val buttonSignup = stringResource(R.string.button_signup)
    val labelHaveAccount = stringResource(R.string.label_have_account)

    Scaffold{
            innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    Text(
                        title,
                        modifier
                            .padding(vertical = 12.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    InputField(
                        labelEmail,
                        email,
                        { email = it },
                        modifier,
                        KeyboardType.Email,
                    )
                    InputField(
                        labelUserName,
                        username,
                        { username = it },
                        modifier,
                        KeyboardType.Text,
                    )
                    InputField(
                        labelFirstName,
                        firstName,
                        { firstName = it },
                        modifier,
                        KeyboardType.Text,
                    )
                    InputField(
                        labelLastName,
                        lastName,
                        { lastName = it },
                        modifier,
                        KeyboardType.Text,
                    )
                    InputField(
                        labelPassword,
                        password,
                        { password = it },
                        modifier,
                        KeyboardType.Password,
                    )
                    InputField(
                        labelPasswordRepeat,
                        passwordRepeat,
                        { passwordRepeat = it },
                        modifier,
                        KeyboardType.Password,
                    )
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var isClicked by remember { mutableStateOf(false) }
                    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
                        val maxWidth = this.maxWidth
                        Button(
                            onClick = { isClicked = true },
                            enabled = arrayOf(email, username, firstName, lastName).all { it.isNotEmpty() }
                                    && password == passwordRepeat,
                            modifier = modifier
                                .width(maxWidth)
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .height(56.dp),
                        ) {
                            Text(buttonSignup, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    if (isClicked) {
                        completeAuthentication(
                            email,
                            password,
                            username,
                            firstName,
                            lastName,
                        )
                    }
                    ClickableText(
                        AnnotatedString(labelHaveAccount),
                        onClick = { navigateToLogin(email) },
                        style = MaterialTheme.typography.labelSmall,
                        modifier = modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}