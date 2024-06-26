package com.example.costsplitter.data.network

import com.example.costsplitter.data.model.UserSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(private val firebase: FirebaseClient) {
    companion object{
        const val USERS_COLLECTION = "users"
    }
    suspend fun createUserTable(userSignIn: UserSignIn) = runCatching{
        val user = hashMapOf(
            "email" to userSignIn.email
        )

        firebase.db
            .collection(USERS_COLLECTION)
            .add(user).await()
    }.isSuccess

}