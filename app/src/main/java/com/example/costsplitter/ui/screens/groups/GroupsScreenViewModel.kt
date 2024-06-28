package com.example.costsplitter.ui.screens.groups

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GroupsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(GroupsScreenUiState())
    val uiState: StateFlow<GroupsScreenUiState> = _uiState

}

data class GroupsScreenUiState(
    val isGroupsLoading: Boolean = false
)