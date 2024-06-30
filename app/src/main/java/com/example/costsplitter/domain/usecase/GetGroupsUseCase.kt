package com.example.costsplitter.domain.usecase

import android.util.Log
import com.example.costsplitter.data.model.Group
import com.example.costsplitter.data.network.AuthenticationService
import com.example.costsplitter.domain.repository.GroupRepository
import java.util.Locale
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
    private val authenticationService: AuthenticationService,
) {
    suspend fun execute(): List<Group> {
        val allGroups = groupRepository.getGroups()
        val currentUserEmail = authenticationService.getCurrentUserMail()?.toLowerCase(Locale.ROOT)
        Log.d("GetGroupsUseCase", "buscando $currentUserEmail :en $allGroups")

        return allGroups.filter { group ->
            group.members.values.any { member ->
                member.email.lowercase() == currentUserEmail
            }
        }
    }
}