package com.example.explodingkittensapp.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM userTable")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
}