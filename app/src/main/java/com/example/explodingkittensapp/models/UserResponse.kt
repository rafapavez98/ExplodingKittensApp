package com.example.explodingkittensapp.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("") var  users:List<User>
        )