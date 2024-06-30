package com.example.costsplitter.domain.usecase

import com.example.costsplitter.data.model.Member
import com.example.costsplitter.domain.repository.GroupRepository
import javax.inject.Inject

class AddMemberToGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(groupId: String, member: Member) {
        groupRepository.addMemberToGroup(groupId, member)
    }
}