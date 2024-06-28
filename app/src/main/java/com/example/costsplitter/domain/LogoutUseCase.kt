package com.example.costsplitter.domain

import com.example.costsplitter.data.network.AuthenticationService
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authenticationService: AuthenticationService) {
    operator fun invoke(): Boolean{
    return authenticationService.logout()
    }
}