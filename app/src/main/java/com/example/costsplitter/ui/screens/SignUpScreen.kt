package com.example.costsplitter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

                uiState.errorMessage?.let { errorMessage ->
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
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

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(
            value = signUpUiState.email,
            onValueChange = onEmailChanged,
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        PasswordField(
            password = signUpUiState.password,
            label = "Password",
            onPasswordChanged = onPasswordChanged,
            onTogglePasswordVisibility = onTogglePasswordVisibility,
            isPasswordVisible = signUpUiState.isPasswordVisible,
            errorText = if (!signUpUiState.isPasswordLengthValid) stringResource(R.string.password_max_6_chars_text) else null,
            isValid = signUpUiState.isPasswordLengthValid
        )

        PasswordField(
            password = signUpUiState.confirmPassword,
            label = "Confirm Password",
            onPasswordChanged = onConfirmPasswordChanged,
            onTogglePasswordVisibility = onToggleConfirmPasswordVisibility,
            isPasswordVisible = signUpUiState.isConfirmPasswordVisible,
            errorText = if (!signUpUiState.isConfirmPasswordSame) stringResource(R.string.passwords_do_not_match) else null,
            isValid = signUpUiState.isConfirmPasswordSame
        )

        OutlinedTextField(
            value = signUpUiState.phone,
            onValueChange = onPhoneNumberChanged,
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )

        Button(
            onClick = onSignUpClicked,
            enabled = signUpUiState.isValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Sign Up")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    label: String,
    isValid: Boolean,
    errorText: String? = null,
    modifier: Modifier = Modifier
) {
    val borderColor = if (!isValid) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline.copy()
    val focusBorderColor = if (!isValid) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary.copy()
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text(label, color = borderColor  ) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { onTogglePasswordVisibility() }
            ) {

                val icon: Painter = rememberVectorPainter(
                    image = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility)
                Icon(
                    painter = icon,
                    contentDescription =
                    if (isPasswordVisible) stringResource(R.string.hide_password)
                    else stringResource(
                        R.string.show_password
                    )
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusBorderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = borderColor
        ),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),

        modifier = modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(4.dp))
    errorText?.let { error ->
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}





@Preview
@Composable
fun SignUpBodyPreview() {
    SignUpBody(
        SignUpUiState(),
        {},
        {},
        {},
        {},
        {},
        {})

}