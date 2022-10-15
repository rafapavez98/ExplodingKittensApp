package com.example.explodingkittensapp.APImodels.Responses

data class APISigninResponse(
    val id: String,
    val email: String,
    val username: String,
    val password: String,
    val total_matches: Int,
    val winrate: Int,
    val friends: MutableList<String>
)
