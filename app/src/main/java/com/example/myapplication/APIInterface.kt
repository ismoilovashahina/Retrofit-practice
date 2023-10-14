package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("/auth/login")
    fun login(@Body login:Login) : Call<User>

    @GET("/products")
    fun getAllProducts() : Call<List<Products>>
}