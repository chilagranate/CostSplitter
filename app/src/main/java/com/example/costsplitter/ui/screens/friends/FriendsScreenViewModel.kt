package com.example.costsplitter.ui.screens.friends

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FriendsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(FriendsScreenUiState())
    val uiState: StateFlow<FriendsScreenUiState> = _uiState

}

data class FriendsScreenUiState(
    val isFriendsLoading: Boolean = false
)
