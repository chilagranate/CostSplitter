package com.example.costsplitter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.costsplitter.CostSplitterTopAppBar
import com.example.costsplitter.R

import com.example.costsplitter.ui.navigation.NavDestination


object SignUpDestination : NavDestination {
    override val route = "signup"
    override val titleRes = R.string.sign_up
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    if (uiState.accountCreated) {
        onNavigateToHome()
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostSplitterTopAppBar(
                title = stringResource(SignUpDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            } else {

                SignUpBody(
                    uiState,
                    viewModel::onEmailChanged,
                    viewModel::onPasswordChanged,
                    viewModel::onConfirmPasswordChanged,
                    viewModel::onPhoneNumberChanged,
                    viewModel::onTogglePasswordVisibility,
                    viewModel::onToggleConfirmPasswordVisibility,
                    viewModel::registerUser,
                )
            }
        }
    }
}


@Composable
fun SignUpBody(
    signUpUiState: SignUpUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    onSignUpClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = signUpUiState.email,
            onValueChange = onEmailChanged,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        PasswordField(
            password = signUpUiState.password,
            label = "Password",
            onPasswordChanged = onPasswordChanged,
            onTogglePasswordVisibility = onTogglePasswordVisibility,
            isPasswordVisible = signUpUiState.isPasswordVisible,
        )

        PasswordField(
            password = signUpUiState.confirmPassword,
            label = "Confirm Password",
            onPasswordChanged = onConfirmPasswordChanged,
            onTogglePasswordVisibility = onToggleConfirmPasswordVisibility,
            isPasswordVisible = signUpUiState.isConfirmPasswordVisible,
        )



        OutlinedTextField(
            value = signUpUiState.phone,
            onValueChange = onPhoneNumberChanged,
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSignUpClicked,
            enabled = signUpUiState.isValid,
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Sign Up")
        }

    }
}


@Composable
fun PasswordField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text(label) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { onTogglePasswordVisibility() }
            ) {
                val image = painterResource(
                    if (isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                )

                Icon(
                    painter = image,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        modifier = modifier.fillMaxWidth()
    )
}


@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        navigateBack = {},
        onNavigateUp = {},
        onNavigateToHome = {})
}
