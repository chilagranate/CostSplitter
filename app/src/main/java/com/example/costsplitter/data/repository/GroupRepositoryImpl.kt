package com.example.costsplitter.data.repository

import com.example.costsplitter.data.model.Group
import com.example.costsplitter.data.model.Member
import com.example.costsplitter.domain.repository.GroupRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GroupRepositoryImpl(
    private val firestore: FirebaseFirestore): GroupRepository {

    companion object {
        const val GROUPS_COLLECTION = "groups"
    }
    override suspend fun getGroup(groupId: String): Group? {
        val doc = firestore.collection(GROUPS_COLLECTION).document(groupId).get().await()
        return doc.toObject(Group::class.java)
    }

    override suspend fun getGroups(): List<Group> {
        val querySnapshot = firestore.collection(GROUPS_COLLECTION).get().await()
        return querySnapshot.documents.mapNotNull { it.toObject(Group::class.java) }
    }

    override suspend fun createGroup(group: Group): String {
        val groupRef = firestore.collection(GROUPS_COLLECTION).add(group).await()
        return groupRef.id
    }

    override suspend fun updateGroup(groupId: String, group: Group) {
        firestore.collection(GROUPS_COLLECTION).document(groupId).set(group).await()
    }

    override suspend fun addMemberToGroup(groupId: String, member: Member) {
        val groupRef = firestore.collection(GROUPS_COLLECTION).document(groupId)
        val updates = mapOf("members.${member.email}" to member)
        groupRef.update(updates).await()
    }
}