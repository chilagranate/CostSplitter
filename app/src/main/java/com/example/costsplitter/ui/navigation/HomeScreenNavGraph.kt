package com.example.costsplitter.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.costsplitter.ui.screens.friends.FriendsScreen
import com.example.costsplitter.ui.screens.groups.GroupsScreen
import com.example.costsplitter.ui.screens.home.BottomNavItem
import com.example.costsplitter.ui.screens.profile.ProfileScreen
import com.example.costsplitter.ui.slideFromLeftAnimation
import com.example.costsplitter.ui.slideFromRightAnimation
import com.example.costsplitter.ui.slideToLeftAnimation
import com.example.costsplitter.ui.slideToRightAnimation

@Composable
 fun HomeScreenNavHost(
    bottomNavController: NavHostController,
    innerPadding: PaddingValues,
    navigateToLogIn: () -> Unit
) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNavItem.Groups.route,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        composable(
            route = BottomNavItem.Groups.route,
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.Friends.route -> slideFromLeftAnimation()
                    BottomNavItem.Profile.route -> slideFromLeftAnimation()
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.Friends.route -> slideToRightAnimation()
                    BottomNavItem.Profile.route -> slideToRightAnimation()
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.Friends.route -> slideFromRightAnimation()
                    BottomNavItem.Profile.route -> slideFromRightAnimation()
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.Friends.route -> slideToLeftAnimation()
                    BottomNavItem.Profile.route -> slideToLeftAnimation()
                    else -> null
                }
            }
        ) {
            GroupsScreen()
        }

        composable(
            route = BottomNavItem.Friends.route,
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.Groups.route -> slideFromRightAnimation()
                    BottomNavItem.Profile.route -> slideFromLeftAnimation()
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.Groups.route -> slideToLeftAnimation()
                    BottomNavItem.Profile.route -> slideToRightAnimation()
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.Groups.route -> slideFromLeftAnimation()
                    BottomNavItem.Profile.route -> slideFromLeftAnimation()
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.Groups.route -> slideToLeftAnimation()
                    BottomNavItem.Profile.route -> slideToLeftAnimation()
                    else -> null
                }
            }
        ) {
            FriendsScreen()
        }
        composable(
            route = BottomNavItem.Profile.route,
            enterTransition = {// Al entrar a la pantalla
                when (initialState.destination.route) {
                    BottomNavItem.Groups.route -> slideFromRightAnimation()
                    BottomNavItem.Friends.route -> slideFromRightAnimation()
                    else -> null
                }
            },
            exitTransition = {// Al salir de la pantalla
                when (targetState.destination.route) {
                    BottomNavItem.Groups.route -> slideToLeftAnimation()
                    BottomNavItem.Friends.route -> slideToLeftAnimation()
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {// Al volver a la pantalla (desde atrás)
                    BottomNavItem.Groups.route -> slideFromLeftAnimation()
                    BottomNavItem.Friends.route -> slideFromLeftAnimation()
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {// Al salir de la pantalla (hacia atrás)
                    BottomNavItem.Groups.route -> slideToLeftAnimation()
                    BottomNavItem.Friends.route -> slideToLeftAnimation()
                    else -> null
                }
            }
        ) {
            ProfileScreen(navigateToLogIn = navigateToLogIn)
        }
    }
}