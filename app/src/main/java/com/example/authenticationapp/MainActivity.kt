package com.example.authenticationapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.authenticationapp.ui.navigator.AuthenticationNavigation
import com.example.authenticationapp.ui.state.AuthenticationState
import com.example.authenticationapp.ui.theme.AuthenticationAppTheme

// https://jonas-rodehorst.dev/blog/how-to-structure-your-jetpack-compose-project
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthenticationAppTheme {
                AuthenticationState() { state, updateState ->
                    AuthenticationNavigation(
                        state,
                        updateState
                    )
                }
            }
        }
    }
}