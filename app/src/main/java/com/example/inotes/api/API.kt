package com.example.inotes.api

import com.example.inotes.model.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("usersList")
    fun getAllUsers() : Call<List<UserModel>>

//    @GET("/api/users?page=2")
//    fun getAllUsersReq() : Call<List<ReqresTestModel>>

//    @GET("/api/users?page=2")
//    fun getAll() : Call<ReqresTestModel>
}