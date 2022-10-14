package com.example.explodingkittensapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    var id: String,
    var email: String,
    var username: String,
    var password: String,
    var total_matches: Int,
    var winrate: Int,
    var friends: MutableList<String>,
): Parcelable