package com.example.costsplitter.domain

import com.example.costsplitter.data.model.UserSignIn
import com.example.costsplitter.data.network.AuthenticationService
import com.example.costsplitter.data.network.UserService
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    suspend operator fun invoke(userSignIn: UserSignIn): Boolean {
        val accountCreated =
            authenticationService.createAccount(userSignIn.email, userSignIn.password) != null
        return if (accountCreated) {
            userService.createUserTable(userSignIn)
        } else {
            false
        }
    }
}