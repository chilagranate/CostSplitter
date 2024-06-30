package com.example.costsplitter.ui.screens.groups

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costsplitter.data.model.Group
import com.example.costsplitter.data.model.Member
import com.example.costsplitter.data.model.Message
import com.example.costsplitter.domain.usecase.CreateGroupResponse
import com.example.costsplitter.domain.usecase.CreateGroupUseCase
import com.example.costsplitter.domain.usecase.GetGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GroupsScreenViewModel"

@HiltViewModel
class GroupsScreenViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val createGroupUseCase: CreateGroupUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupsScreenUiState())
    val uiState: StateFlow<GroupsScreenUiState> = _uiState

    init {
        val group =  generateTestGroup()
        //createGroup(group)
        _uiState.value = uiState.value.copy(isLoading = true)
        loadGroups()
    }

    private fun loadGroups() {
        viewModelScope.launch {
            try {
                val groups = getGroupsUseCase.execute()
                _uiState.value = uiState.value.copy(groups = groups, isLoading = false)

            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(isLoading = false)
                Log.e(TAG, "Error loading groups", e)
                // Manejar error de carga de grupos
            }
        }
    }
    fun createGroup(group: Group) {
        viewModelScope.launch {
            when (val response = createGroupUseCase.invoke(group)) {
                is CreateGroupResponse.Success -> {
                    // Manejar éxito, actualizar estado de la UI, etc.
                    loadGroups()
                }
                is CreateGroupResponse.Error -> {
                    // Manejar error, mostrar mensaje al usuario, etc.
                    _uiState.value = uiState.value.copy(
                        // Actualiza el estado con el mensaje de error
                    )
                }
            }
        }
    }
    private fun generateTestGroup(): Group {
        val members = mapOf(
            "member1@example.com" to Member("member1@example.com", "Member One" ),
            "member2@example.com" to Member("member2@example.com", "Member Two")
        )

        val messages = listOf(
            Message("Hello", "admin@example.com", sender = "sender@example.com", timestamp =  System.currentTimeMillis()),
            Message("welcome", "admin@example.com", sender = "sender@example.com", timestamp =  System.currentTimeMillis()),
        )

        return Group(
            id = "testGroupId",
            name = "Test Group",
            members = members,
            photoUrl = "http://example.com/photo.jpg",
            messageBoard = messages,
            admin = "admin@example.com"
        )
    }


}






data class GroupsScreenUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val groups: List<Group> = emptyList()
)