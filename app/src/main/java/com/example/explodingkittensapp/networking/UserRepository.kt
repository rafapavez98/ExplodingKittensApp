package com.example.explodingkittensapp.networking

import com.example.explodingkittensapp.APImodels.Bodies.APIUser
import com.example.explodingkittensapp.APImodels.Responses.APISigninResponse
import com.example.explodingkittensapp.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRepository {
    @POST("register")
    fun createUser(@Body user: APIUser): Call<APISigninResponse>
}