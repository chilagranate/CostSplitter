package com.example.costsplitter.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costsplitter.data.network.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val authenticationService: AuthenticationService) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun onBottomItemSelected(index: Int) {
        _uiState.value = _uiState.value.copy(bottomItemSelected = index)
    }

    fun logout() {
        viewModelScope.launch {
            val loggedOut = authenticationService.logout()
            if (loggedOut) {
                _uiState.value = HomeScreenUiState(isLoggedIn = false)
                // Manejar la navegación o la actualización de la interfaz de usuario después del logout
            } else {
                // Manejar caso de error en el cierre de sesión
            }
        }
    }
}


    data class HomeScreenUiState(
        val isLoading: Boolean = false,
        val isLoggedIn: Boolean = true,
        val bottomItemSelected: Int = 0
    )