package com.example.securityrtcs.net

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetApi {
    //@POST("/auth/login/")
    //fun login (@Body hashMap: HashMap<String, String>): retrofit2.Call<login>
    @GET("/")
    fun getInfo():retrofit2.Call<ArrayList<moduleinfo>>
}