package com.example.costsplitter.ui.screens


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costsplitter.data.model.UserSignIn
import com.example.costsplitter.data.response.CreateAccountResponse
import com.example.costsplitter.domain.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
private const val TAG = "SignUpScreenViewModel"

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private val createAccountUseCase: CreateAccountUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }


    fun onEmailChanged(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            isValid = validateFields(
                newEmail,
                _uiState.value.password,
                _uiState.value.confirmPassword
            )
        )
    }


    fun onPasswordChanged(newPassword: String) {
        _uiState.value = _uiState.value.copy(
            password = newPassword,
            isPasswordLengthValid = validatePasswordLength(newPassword),
            isConfirmPasswordSame = validateConfirmPassword(_uiState.value.confirmPassword, newPassword),
            isValid = validateFields(
                _uiState.value.email,
                newPassword,
                _uiState.value.confirmPassword
            )
        )
    }



    fun onConfirmPasswordChanged(newConfirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = newConfirmPassword,
            isConfirmPasswordSame = validateConfirmPassword(_uiState.value.password, newConfirmPassword),
            isValid = validateFields(
                _uiState.value.email,
                _uiState.value.password,
                newConfirmPassword
            )
        )
    }

    fun validatePasswordLength(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
    fun onPhoneNumberChanged(newPhoneNumber: String) {
        _uiState.value = _uiState.value.copy(phone = newPhoneNumber)
    }


    fun onTogglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    fun onToggleConfirmPasswordVisibility() {
        _uiState.value =
            _uiState.value.copy(isConfirmPasswordVisible = !_uiState.value.isConfirmPasswordVisible)
    }

    private fun validateFields(email: String, password: String, confirmPassword: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword && password.length >= MIN_PASSWORD_LENGTH
    }

    fun registerUser() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")
            val userSignIn = UserSignIn(
                email = _uiState.value.email,
                password = _uiState.value.password,
            )
            when (createAccountUseCase(userSignIn)) {
                CreateAccountResponse.Success -> {
                    Log.i(TAG, "Account creation succeeded")
                    _uiState.value = _uiState.value.copy(accountCreated = true)
                    // Navegar a la pantalla de inicio o realizar otra acción
                }
                CreateAccountResponse.AccountAlreadyExists -> {
                    Log.i(TAG, "Account already exists")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Account already exists"
                    )
                }
                CreateAccountResponse.Error -> {
                    Log.e(TAG, "Account creation failed")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Account creation failed"
                    )
                }
            }
        }
    }


}


data class SignUpUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isValid: Boolean = false,
    val isPasswordLengthValid: Boolean = false,
    val isConfirmPasswordSame: Boolean = false,
    val accountCreated: Boolean = false
)