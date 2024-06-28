package com.example.costsplitter.ui.screens.friends


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FriendsScreen(
    viewModel: FriendsScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FriendsScreenBody()
}

@Composable
fun FriendsScreenBody(
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text("Friends Screen")
    }
}
