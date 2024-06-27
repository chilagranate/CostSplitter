package com.example.costsplitter.data.response

sealed class CreateAccountResponse {
    object Success : CreateAccountResponse()
    object AccountAlreadyExists : CreateAccountResponse()
    object Error : CreateAccountResponse()
}