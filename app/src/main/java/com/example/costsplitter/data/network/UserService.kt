package com.example.costsplitter.data.network

import android.util.Log
import com.example.costsplitter.data.model.UserSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(private val firebase: FirebaseClient) {
    companion object{
        const val USERS_COLLECTION = "users"
    }
    // Crear un usuario placeholder en Firestore
    suspend fun createPlaceholderUser(email: String): Boolean = runCatching {
        val user = hashMapOf(
            "email" to email,
            "isPlaceholder" to true
        )

        firebase.db.collection(USERS_COLLECTION)
            .add(user).await()
    }.isSuccess

    // Actualizar un usuario placeholder con el UID de Firebase Auth
    suspend fun linkPlaceholderUser(userId: String, email:String): Boolean = runCatching {
        val querySnapshot = firebase.db.collection(USERS_COLLECTION)
            .whereEqualTo("email", email)
            .whereEqualTo("isPlaceholder", true)
            .get().await()

        if (querySnapshot.documents.isEmpty()) {
            return false
        }

        val documentSnapshot = querySnapshot.documents.first()
        val tempUid = documentSnapshot.id
        firebase.db.collection(USERS_COLLECTION).document(tempUid).delete().await()
        val user = hashMapOf(
            "email" to email,
            "isPlaceholder" to false,
            "uid" to userId
        )
        firebase.db.collection(USERS_COLLECTION).document(userId).set(user).await()
    }.onSuccess {
        Log.d("UserService", "Placeholder user linked successfully")
    }.onFailure {
        Log.e("UserService", "Failed to link placeholder user: ${it.message}")
    }.isSuccess

    suspend fun createUserInFirestore(userId: String, email:String): Boolean = runCatching {
        val user = hashMapOf(
            "uid" to userId,
            "email" to email,
            "isPlaceholder" to false
        )
        firebase.db
            .collection(USERS_COLLECTION)
            .document(userId)
            .set(user)
            .await()
    }.onSuccess {
        Log.d("UserService", "User created in Firestore successfully")
    }.onFailure {
        Log.e("UserService", "Failed to create user in Firestore: ${it.message}")
    }.isSuccess

}