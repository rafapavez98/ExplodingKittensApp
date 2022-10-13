package com.example.explodingkittensapp.api

import com.example.explodingkittensapp.models.SigninResponse
import com.example.explodingkittensapp.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIService {
    @FormUrlEncoded
    @POST("register")
    fun createUser(
        @Field("email") email:String,
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("total matches") total_matches:Int,
        @Field("winrate") winrate:Int,
        @Field("friends") friends:List<User>
    ): Call<SigninResponse>
}