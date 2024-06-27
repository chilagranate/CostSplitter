package com.example.costsplitter.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costsplitter.data.response.LoginResult
import com.example.costsplitter.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            isValid = validateFields(newEmail, _uiState.value.password)
        )
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.value = _uiState.value.copy(
            password = newPassword,
            isValid = validateFields(_uiState.value.email, newPassword)
        )
    }

    fun onTogglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    private fun validateFields(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }


    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            when (val result = loginUseCase(uiState.value.email, uiState.value.password)) {

                LoginResult.Error -> {
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, errorMessage = "Login Failed")
                }

                is LoginResult.Success -> {
                    if (result.verified) {
                        _uiState.value = _uiState.value.copy(isSignedIn = true)
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    } else {
                        _uiState.value = _uiState.value.copy(isSignedIn = true)
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                }
            }
        }
    }
}


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isValid: Boolean = false,
    val isSignedIn: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val errorMessage: String? = null
)