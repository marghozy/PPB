package com.example.playarena.data.repository

import com.example.playarena.data.dao.PlaySessionDao
import com.example.playarena.data.entity.PlaySession

class PlaySessionRepository(
    private val sessionDao: PlaySessionDao
) {

    fun getAllSessions() =
        sessionDao.getAllSessions()

    fun getSessionsByMember(
        memberId: Int
    ) = sessionDao.getSessionsByMember(memberId)

    suspend fun insertSession(
        session: PlaySession
    ) {
        sessionDao.insertSession(session)
    }
}