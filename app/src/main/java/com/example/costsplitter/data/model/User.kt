package com.example.costsplitter.data.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val profilePhotoUrl: String? = null,
    val emailVerified: Boolean = false,
    val phoneVerified: Boolean = false
)