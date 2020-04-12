package com.example.inotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsersTable")
data class UserEntity (
    var name : String,
    var store : String
){
    @PrimaryKey(autoGenerate = true)
    var Id : Int = 0
}