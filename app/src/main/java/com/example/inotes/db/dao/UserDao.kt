package com.example.inotes.db.dao

import androidx.room.*
import com.example.inotes.db.UserEntity
import com.example.inotes.model.UserModel
import retrofit2.Response

@Dao
interface UserDao {

    @Query("SELECT * FROM UsersTable")
    fun getAllUsers() : List<UserModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllUsers(user: ArrayList<UserEntity>)
}