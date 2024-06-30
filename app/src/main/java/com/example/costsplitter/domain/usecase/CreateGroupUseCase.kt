package com.example.costsplitter.domain.usecase

import com.example.costsplitter.data.model.Group
import com.example.costsplitter.domain.repository.GroupRepository
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(group: Group): CreateGroupResponse {
        return try {
            val groupId = groupRepository.createGroup(group)
            CreateGroupResponse.Success(groupId)
        } catch (e: Exception) {
            CreateGroupResponse.Error(e.message ?: "Unknown error") //TODO add error message
        }
    }
}

sealed class CreateGroupResponse {
    data class Success(val groupId: String) : CreateGroupResponse()
    data class Error(val message: String) : CreateGroupResponse()
}