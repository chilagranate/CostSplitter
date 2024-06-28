package com.example.costsplitter.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.costsplitter.CostSplitterTopAppBar
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
    if (uiState.isSignedIn) navigateToHome()

    Scaffold(
        topBar = {
            CostSplitterTopAppBar(
                title = stringResource(LoginDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (uiState.isLoading) {
                LoadingOverlay()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
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
                        isError = uiState.isError,
                        errorMessage = uiState.errorMessage,
                        onTogglePasswordVisibility = viewModel::onTogglePasswordVisibility,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0x80000000)) // Semi-transparent gray
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
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
    isError: Boolean,
    errorMessage: String?,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    isEntryValid: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
    ) {
        UserTextField(
            email = email,
            password = password,
            isPasswordVisible = isPasswordVisible,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            isError = isError,
            errorMessage = errorMessage,
            onTogglePasswordVisibility = onTogglePasswordVisibility,
        )

        Button(
            onClick = onSignIn,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = isEntryValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Sign in")
        }

        OutlinedButton(
            onClick = navigateToSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Sign up", color = MaterialTheme.colorScheme.secondary)
        }

        Button(
            onClick = { /* TODO: Handle Google Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Log In with Google")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    isError: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    onTogglePasswordVisibility: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val borderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline.copy()

    OutlinedTextField(
        value = email,
        onValueChange = onEmailChanged,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = borderColor
        ),
        keyboardActions = KeyboardActions(
            onNext = { passwordFocusRequester.requestFocus() }
        ),
        label = { Text("Email") },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text("Password") },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                val icon: Painter = rememberVectorPainter(
                    image = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                )
                Icon(
                    painter = icon,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = borderColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )

    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}


