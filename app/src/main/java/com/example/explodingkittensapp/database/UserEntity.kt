package com.example.explodingkittensapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "total_matches") var total_matches: Int,
    @ColumnInfo(name = "winrate") var winrate: Int,
    @ColumnInfo(name = "friends") var friends: MutableList<String>,
    @ColumnInfo(name = "cards") var cards: MutableList<String>,
    @ColumnInfo(name = "lastgame") var lastgame: String

)

