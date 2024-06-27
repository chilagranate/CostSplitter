package com.example.costsplitter.data.response

sealed class CreateAccountResponse {
    data object Success : CreateAccountResponse()
    data object AccountAlreadyExists : CreateAccountResponse()
    data object Error : CreateAccountResponse()
}