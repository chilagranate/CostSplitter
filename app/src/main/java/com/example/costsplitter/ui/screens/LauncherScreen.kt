package com.example.costsplitter.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.costsplitter.IconApp
import com.example.costsplitter.R


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
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            IconApp()
            CircularProgressIndicator()


        }
    }
}

@Preview
@Composable
fun LauncherScreenPreview() {
    LauncherContent()
}