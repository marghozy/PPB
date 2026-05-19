package com.example.mvvmapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmapp.data.local.entity.User

@Dao
interface UserDao {

    @Query(
        "SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1"
    )
    suspend fun login(
        username: String,
        password: String
    ): User?

    @Insert
    suspend fun insert(user: User)
}