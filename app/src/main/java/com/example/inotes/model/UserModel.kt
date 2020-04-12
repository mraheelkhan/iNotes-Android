package com.example.inotes.model


data class UserModel (
    val name : String,
    val store : Int
)

data class Users(
    val users: List<UserModel>
)