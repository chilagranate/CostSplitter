package com.example.costsplitter.domain

import com.example.costsplitter.data.network.AuthenticationService
import com.example.costsplitter.data.response.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthenticationService){
    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email,password)
}