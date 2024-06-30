package com.example.costsplitter.domain.repository

import com.example.costsplitter.data.model.Group
import com.example.costsplitter.data.model.Member

interface GroupRepository {
    suspend fun createGroup(group: Group): String
    suspend fun getGroup(groupId: String): Group?
    suspend fun getGroups(): List<Group>
    suspend fun updateGroup(groupId: String, group: Group)
    suspend fun addMemberToGroup(groupId: String, member: Member)

}