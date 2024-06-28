package com.example.costsplitter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.costsplitter.ui.screens.launcher.LauncherScreen
import androidx.compose.ui.Modifier
import com.example.costsplitter.ui.screens.home.HomeScreen
import com.example.costsplitter.ui.screens.home.HomeScreenDestination
import com.example.costsplitter.ui.screens.login.LoginDestination
import com.example.costsplitter.ui.screens.login.LoginScreen
import com.example.costsplitter.ui.screens.login.SignUpDestination
import com.example.costsplitter.ui.screens.login.SignUpScreen

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
                navigateToHome = { navController.navigate(HomeScreenDestination.route) },
                navigateToLogin = { navController.navigate(LoginDestination.route) }
            )
        }
        composable(route = "login") {
            LoginScreen(
                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                navigateToHome = { navController.navigate(HomeScreenDestination.route) }
            )
        }

        composable(route = "signup") {
            SignUpScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                onNavigateToHome = { navController.navigate(HomeScreenDestination.route) }
            )
        }

        composable(route = "home") {
            HomeScreen(
                navigateToLogIn = { navController.navigate(LoginDestination.route)})
        }
    }
}