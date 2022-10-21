package com.example.explodingkittensapp.APImodels.Bodies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class APIUser(
    var email: String,
    var username: String,
    var password: String,
    var total_matches: Int,
    var winrate: Int,
    var friends: MutableList<String>,
    var cards: MutableList<String>
): Parcelable