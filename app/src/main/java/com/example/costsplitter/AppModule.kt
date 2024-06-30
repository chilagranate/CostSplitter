package com.example.costsplitter


import com.example.costsplitter.data.model.User
import com.example.costsplitter.data.repository.GroupRepositoryImpl
import com.example.costsplitter.domain.repository.GroupRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideGroupRepository(firestore: FirebaseFirestore): GroupRepository {
        return GroupRepositoryImpl(firestore)
    }

    @Provides
    fun provideCurrentUser(): User {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return if (firebaseUser != null) {
            User(email = firebaseUser.email ?: "", name = firebaseUser.displayName ?: "")
        } else {
            User(email = "", name = "")
        }
    }
}