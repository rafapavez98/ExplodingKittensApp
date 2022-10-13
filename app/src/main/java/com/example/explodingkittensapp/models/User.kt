package com.example.explodingkittensapp.models

import com.google.gson.annotations.SerializedName

data class User(
    val id:String,
    val winrate:Int,
    @SerializedName("total matches") val total_matches:Int,
    val password:String,
    val username:String,
    val email:String,
    val friends:List<User>
)



