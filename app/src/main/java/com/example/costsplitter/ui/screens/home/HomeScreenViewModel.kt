package com.example.costsplitter.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() :
    ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun onBottomItemSelected(index: Int) {
        _uiState.value = _uiState.value.copy(bottomItemSelected = index)
    }

    init {
        _uiState.value = _uiState.value.copy(isLoading = false)
    }
}


    data class HomeScreenUiState(
        val isLoading: Boolean = true,
        val isLoggedIn: Boolean = true,
        val bottomItemSelected: Int = 0
    )