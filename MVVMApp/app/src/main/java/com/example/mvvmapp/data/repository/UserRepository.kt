package com.example.mvvmapp.data.repository

import com.example.mvvmapp.data.local.dao.UserDao
import com.example.mvvmapp.data.local.entity.User

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun login(
        username: String,
        password: String
    ): User? {

        return userDao.login(
            username,
            password
        )
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}