package com.example.costsplitter.data.response

sealed class LoginResult{
    data object InvalidCredentials : LoginResult()
    data object UserNotFound : LoginResult()
    data object NetworkError : LoginResult()
    data object Error : LoginResult()
    data class Success(val verified: Boolean): LoginResult()
}