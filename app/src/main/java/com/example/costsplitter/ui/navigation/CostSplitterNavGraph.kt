package com.example.costsplitter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.costsplitter.ui.screens.home.HomeScreen
import com.example.costsplitter.ui.screens.home.HomeScreenDestination
import com.example.costsplitter.ui.screens.login.LoginDestination
import com.example.costsplitter.ui.screens.login.LoginScreen
import com.example.costsplitter.ui.screens.login.SignUpDestination
import com.example.costsplitter.ui.screens.login.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CostSplitterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val isLoggedIn = firebaseAuth.currentUser != null
    val startDestination: String = if (isLoggedIn) {
        HomeScreenDestination.route
    } else {
        LoginDestination.route
    }
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {

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
                navigateToLogIn = { navController.navigate(LoginDestination.route) })
        }
    }
}