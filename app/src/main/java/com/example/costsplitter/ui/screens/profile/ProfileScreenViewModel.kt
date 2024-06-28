package com.example.costsplitter.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costsplitter.data.network.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val authenticationService: AuthenticationService) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState: StateFlow<ProfileScreenUiState> = _uiState

    init {
        viewModelScope.launch {
                val userMail = authenticationService.getCurrentUserMail()
                _uiState.value = _uiState.value.copy(
                    userEmail = userMail?:""
                )
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        if (authenticationService.logout()) {
            onLogoutSuccess()
        }
    }
}



data class ProfileScreenUiState(
    val isProfileLoading: Boolean = false,
    val userEmail: String = ""
)