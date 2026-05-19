package com.example.mvvmapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.local.entity.User
import com.example.mvvmapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var username = mutableStateOf("")

    var password = mutableStateOf("")

    var loginState by mutableStateOf("")
        private set

    fun login() {

        viewModelScope.launch {

            val user = repository.login(
                username.value,
                password.value
            )

            loginState =
                if (user != null) {
                    "Login Berhasil"
                } else {
                    "Username atau Password Salah"
                }
        }
    }

    fun register() {

        viewModelScope.launch {

            repository.insert(
                User(
                    username = username.value,
                    password = password.value
                )
            )

            loginState = "Register Berhasil"
        }
    }

    fun insertDummyUser() {

        viewModelScope.launch {

            repository.insert(
                User(
                    username = "admin",
                    password = "12345"
                )
            )
        }
    }
}