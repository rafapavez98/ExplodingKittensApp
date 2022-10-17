package com.example.explodingkittensapp.networking

import com.example.explodingkittensapp.APImodels.Bodies.*
import com.example.explodingkittensapp.APImodels.Responses.APIFriendsResponse
import com.example.explodingkittensapp.APImodels.Responses.APILoginResponse
import com.example.explodingkittensapp.APImodels.Responses.APIMessageResponse
import com.example.explodingkittensapp.APImodels.Responses.APISigninResponse
import com.example.explodingkittensapp.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface FriendInviteRemoteRepository {
    @POST("finivte")
    fun createInvite(@Body invite: APIFinvite): Call<APIMessageResponse>

    @POST("acceptinvite")
    fun acceptInvite(@Body login: APIAcceptInvite): Call<APIMessageResponse>

    @POST("rejectinvite")
    fun rejectInvite(@Body login: APIRejectInvite): Call<APIMessageResponse>

}