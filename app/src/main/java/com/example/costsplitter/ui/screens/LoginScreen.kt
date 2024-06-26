package com.example.costsplitter.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.costsplitter.CostSplitterTopAppBar
import com.example.costsplitter.R
import com.example.costsplitter.ui.navigation.NavDestination

object LoginDestination : NavDestination {
    override val route = "login"
    override val titleRes = R.string.login_title
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToHome:()->Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    //If logged go to home
    if (uiState.isSignedIn) navigateToHome()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostSplitterTopAppBar(
                title = stringResource(LoginDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
        ) {
            LoginBody(
                email = uiState.email,
                password = uiState.password,
                onEmailChanged = viewModel::onEmailChanged,
                onPasswordChanged = viewModel::onPasswordChanged,
                navigateToSignUp = navigateToSignUp,
                onSignIn = viewModel::login,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Composable
private fun LoginBody(
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    navigateToSignUp: () -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val passwordFocusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            ),
            label = { Text("Email") },
            modifier = modifier
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* handle done action if needed */ }
            ),
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier.focusRequester(passwordFocusRequester)
        )

        Button(
            onClick = onSignIn,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),

            modifier = modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Sign in")
        }

        OutlinedButton(
            onClick =  navigateToSignUp ,

            modifier = modifier
                .fillMaxWidth()

        ) {
            Text(text = "Sign up", color = MaterialTheme.colorScheme.secondary)
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Log In with Google")
        }
    }
}


@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(navigateToSignUp ={}, navigateToHome = {})
}