package com.example.costsplitter.ui.screens.groups

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.costsplitter.ui.screens.friends.FriendsScreenViewModel

@Composable
fun GroupsScreen(
    viewModel: GroupsScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    GroupsScreenBody()
}

@Composable
fun GroupsScreenBody(
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text("Groups Screen")
    }
}