package com.example.inotes.retrofit

import com.example.inotes.api.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "http://192.168.8.101/androidapi/public/"
//    private const val BASE_URL = "https://api.github.com/users/list"

    val instance: API by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(API::class.java)
    }
}