package com.example.costsplitter.domain

import android.util.Log
import com.example.costsplitter.data.model.UserSignIn
import com.example.costsplitter.data.network.AuthenticationService
import com.example.costsplitter.data.network.UserService
import com.example.costsplitter.data.response.CreateAccountResponse
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {
    companion object {
        private const val TAG = "CreateAccountUseCase"
    }

    suspend operator fun invoke(userSignIn: UserSignIn): CreateAccountResponse {


        val result = authenticationService.createAccount(userSignIn.email, userSignIn.password)
        return when (result) {
            CreateAccountResponse.Success -> {
                val userId =  authenticationService.getCurrentUserId()

                if (userId != null) {
                    if (userService.linkPlaceholderUser(userId, userSignIn.email) || userService.createUserInFirestore(userId, userSignIn.email)) {
                        CreateAccountResponse.Success
                    } else {
                        CreateAccountResponse.Error
                    }
                } else {
                    CreateAccountResponse.Error
                }
            }
            CreateAccountResponse.AccountAlreadyExists -> result
            CreateAccountResponse.Error -> result
        }
    }
}