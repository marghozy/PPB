package com.example.playarena.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playarena.data.entity.PlaySession
import com.example.playarena.data.repository.PlaySessionRepository
import kotlinx.coroutines.launch

class PlaySessionViewModel(
    private val repository: PlaySessionRepository
) : ViewModel() {

    val sessions =
        repository.getAllSessions()

    fun addSession(
        memberId: Int,
        duration: Int,
        consoleType: String,
        pointEarned: Int,
        date: String
    ) {

        viewModelScope.launch {

            repository.insertSession(
                PlaySession(
                    memberId = memberId,
                    duration = duration,
                    consoleType = consoleType,
                    pointEarned = pointEarned,
                    date = date
                )
            )
        }
    }
}