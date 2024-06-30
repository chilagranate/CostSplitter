package com.example.costsplitter.data.repository

import com.example.costsplitter.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val firestore: FirebaseFirestore) {

    suspend fun getUser(uid: String): User? {
        val doc = firestore.collection("users").document(uid).get().await()
        return doc.toObject(User::class.java)
    }

    suspend fun createUser(user: User) {
        firestore.collection("users").document(user.uid).set(user).await()
    }

    suspend fun updateUser(user: User) {
        firestore.collection("users").document(user.uid).set(user).await()
    }
}