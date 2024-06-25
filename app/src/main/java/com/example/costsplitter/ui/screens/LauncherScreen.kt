package com.example.costsplitter.ui.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.costsplitter.R
import com.example.costsplitter.ui.navigation.NavDestination
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel



object LauncherScreenDestination: NavDestination {
    override val route= "launcher"
    override val titleRes= R.string.app_name
}


@Composable
fun LauncherScreen(
    viewModel: LauncherViewModel = viewModel(),
    navigateToLogin: () -> Unit,
    modifier: Modifier= Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()

        LaunchedEffect(Unit) {
            delay(2000)  // Espera de 2 segundos
            navigateToLogin()
        }
    }
}