package com.example.costsplitter.data.model

import com.google.firebase.firestore.PropertyName


data class Group(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",

    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",

    @get:PropertyName("members")
    @set:PropertyName("members")
    var members: Map<String, Member> = emptyMap(),

    @get:PropertyName("photoUrl")
    @set:PropertyName("photoUrl")
    var photoUrl: String? = null,

    @get:PropertyName("messageBoard")
    @set:PropertyName("messageBoard")
    var messageBoard: List<Message> = emptyList(),

    @get:PropertyName("admin")
    @set:PropertyName("admin")
    var admin: String = ""
) {
    constructor() : this("", "", emptyMap(), null, emptyList(), "")
}

data class Member(
    val email: String = "",
    val uid: String? = null, // Will be null for members not yet registered
    val permissions: Permissions = Permissions()
)

data class Permissions(
    val canEdit: Boolean = false,
    val canDelete: Boolean = false
)

data class Message(
    val id: String = "",
    val content: String = "",
    val sender: String = "",
    val timestamp: Long = 0
)