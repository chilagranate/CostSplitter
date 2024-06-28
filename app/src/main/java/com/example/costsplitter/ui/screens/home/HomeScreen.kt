package com.example.costsplitter.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.costsplitter.CostSplitterTopAppBar
import com.example.costsplitter.R
import com.example.costsplitter.ui.navigation.NavDestination
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.costsplitter.AppNavigationBar
import com.example.costsplitter.ui.screens.friends.FriendsScreen
import com.example.costsplitter.ui.screens.groups.GroupsScreen
import com.example.costsplitter.ui.screens.profile.ProfileScreen

sealed class BottomNavItem(val label: String, val icon: ImageVector, val route: String) {
    data object Groups : BottomNavItem("Groups", Icons.Default.Groups, "groups")
    data object Friends : BottomNavItem("Friends", Icons.Default.Person, "friends")
    data object Profile : BottomNavItem("Profile", Icons.Default.AccountCircle, "profile")
}


object HomeScreenDestination : NavDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToLogIn: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val bottomNavController = rememberNavController()

    if (!uiState.isLoggedIn) {
        navigateToLogIn()
    }
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostSplitterTopAppBar(
                title = stringResource(HomeScreenDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            val bottomNavItems = listOf(
                BottomNavItem.Groups,
                BottomNavItem.Friends,
                BottomNavItem.Profile
            )
            AppNavigationBar(
                items = bottomNavItems,
                selectedItemIndex = uiState.bottomItemSelected,
                onItemSelected = { index ->
                    viewModel.onBottomItemSelected(index)
                    val route = bottomNavItems[index].route
                    bottomNavController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
            )
        },
        content = { innerPadding ->
            NavHost(
                navController = bottomNavController,
                startDestination = BottomNavItem.Groups.route,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(BottomNavItem.Groups.route) {
                    GroupsScreen()
                }
                composable(BottomNavItem.Friends.route) {
                    FriendsScreen()
                }
                composable(BottomNavItem.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    )
//            when(uiState.bottomItemSelected){
//                0 -> GroupsScreen()
//                1 -> FriendsScreen()
//                2 -> ProfileScreen()
//            }

}


@Composable
fun HomeScreenBody(
    onLogOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()

    ) {
        Button(
            onClick = onLogOut,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Log Out")
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenBody({})
}
