package com.example.costsplitter.ui.screens.launcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.costsplitter.IconApp


//object LauncherScreenDestination: NavDestination {
//    override val route= "launcher"
//    override val titleRes= R.string.app_name
//}


@Composable
fun LauncherScreen(
    modifier: Modifier= Modifier,
    viewModel: LauncherViewModel = viewModel(),
    navigateToHome: ()-> Unit,
    navigateToLogin: () -> Unit
    ) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    LauncherContent()

    if(isLoggedIn){
        navigateToHome()
    }else{
        navigateToLogin()
    }
}

@Composable
fun LauncherContent(
    modifier: Modifier= Modifier,
){
    Surface (
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconApp()
            CircularProgressIndicator(modifier = Modifier.padding(top = 36.dp))
        }
    }
}

@Preview
@Composable
fun LauncherScreenPreview() {
    LauncherContent()
}