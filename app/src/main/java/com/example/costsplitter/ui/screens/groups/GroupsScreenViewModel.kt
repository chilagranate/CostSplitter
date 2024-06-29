package com.example.costsplitter.ui.screens.groups

import androidx.lifecycle.ViewModel
import com.example.costsplitter.data.model.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GroupsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(GroupsScreenUiState())
    val uiState: StateFlow<GroupsScreenUiState> = _uiState
    init {
        _uiState.value = _uiState.value.copy(groups = crateFakeGroups())
    }

}

private fun crateFakeGroups(): List<Group> {
    return listOf(
        Group("Grupo 1", 100.0),
        Group("Grupo 2", 200.0),
        Group("Grupo 3", 300.0),
    )
}

data class GroupsScreenUiState(
    val isGroupsLoading: Boolean = false,
    val groups: List<Group> = emptyList()
)