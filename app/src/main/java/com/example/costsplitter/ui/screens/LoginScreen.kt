package com.example.costsplitter.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.costsplitter.IconApp
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
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    //If logged go to home
    if (uiState.isSignedIn) navigateToHome()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconApp(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(horizontal = 50.dp)
        )
        LoginBody(
            email = uiState.email,
            password = uiState.password,
            isPasswordVisible = uiState.isPasswordVisible,
            onEmailChanged = viewModel::onEmailChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            navigateToSignUp = navigateToSignUp,
            onSignIn = viewModel::login,
            isEntryValid = uiState.isValid,
            onTogglePasswordVisibility = viewModel::onTogglePasswordVisibility,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
private fun LoginBody(
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    navigateToSignUp: () -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    isEntryValid: Boolean = false,

    ) {
    val passwordFocusRequester = remember { FocusRequester() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
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
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        PasswordField(
            password = password,
            label = "Password",
            onPasswordChanged = onPasswordChanged,
            onTogglePasswordVisibility = onTogglePasswordVisibility,
            isPasswordVisible = isPasswordVisible,
            isValid = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester)
                .padding(bottom = 8.dp)
        )


        Button(
            onClick = onSignIn,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = isEntryValid,

            modifier = modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Sign in")
        }

        OutlinedButton(
            onClick = navigateToSignUp,

            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        ) {
            Text(text = "Sign up", color = MaterialTheme.colorScheme.secondary)
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Log In with Google")
        }
    }
}


