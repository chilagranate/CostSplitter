package com.example.costsplitter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.costsplitter.ui.screens.LauncherScreen
import androidx.compose.ui.Modifier
import com.example.costsplitter.ui.screens.LoginDestination
import com.example.costsplitter.ui.screens.LoginScreen

@Composable
fun CostSplitterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = "launcher", modifier = modifier
    ) {
        composable(route = "launcher") {
            LauncherScreen(
                navigateToLogin = { navController.navigate(LoginDestination.route)}
            )
        }
        composable(route = "login") {
            LoginScreen()
        }

    }

}