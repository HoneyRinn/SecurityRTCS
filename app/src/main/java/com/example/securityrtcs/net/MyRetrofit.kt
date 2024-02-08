package com.example.securityrtcs.net

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MyRetrofit {
    fun getRetrofit(): RetApi = Retrofit.Builder()
        .baseUrl("/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetApi::class.java)
}