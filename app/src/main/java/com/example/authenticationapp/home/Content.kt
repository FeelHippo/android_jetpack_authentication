package com.example.authenticationapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authenticationapp.R
import com.example.authenticationapp.ui.state.AuthenticationStateData

@Composable
fun HomeScreen(
    uiState: AuthenticationStateData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Welcome ${uiState.userModel?.firstName ?: ""} ${uiState.userModel?.lastName ?: ""}")
    }
}

@Composable
fun ProfileScreen(
    uiState: AuthenticationStateData,
    revokeAuthentication: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Profile Screen for ${uiState.userModel?.email ?: ""}")
            Button(onClick = { revokeAuthentication() }) {
                Text(stringResource(R.string.logout))
            }
        }
    }
}

@Composable
fun MapScreen(
    uiState: AuthenticationStateData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Map Screen ${uiState.userModel?.username ?: ""}")
    }
}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    HOME("home", "Welcome", Icons.Default.Home),
    MAP("map", "Map", Icons.Default.Search),
    PROFILE("profile", "Profile", Icons.Default.Person),
}

@Composable
fun AppNavHost(
    uiState: AuthenticationStateData,
    navController: NavHostController,
    startDestination: Destination,
    revokeAuthentication: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = startDestination.route,
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.HOME -> HomeScreen(
                        uiState = uiState,
                        modifier,
                    )
                    Destination.MAP -> MapScreen(
                        uiState = uiState,
                        modifier
                    )
                    Destination.PROFILE -> ProfileScreen(
                        uiState = uiState,
                        revokeAuthentication,
                        modifier
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    uiState: AuthenticationStateData,
    revokeAuthentication: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.label,
                                modifier = modifier,
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        },
    ) { contentPadding  ->
        AppNavHost(
            uiState = uiState,
            navController,
            startDestination,
            revokeAuthentication,
            modifier = Modifier.padding(contentPadding
            )
        )
    }
}