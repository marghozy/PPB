package com.example.playarena.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.playarena.data.entity.PlaySession
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaySessionDao {

    @Insert
    suspend fun insertSession(
        session: PlaySession
    )

    @Query("""
        SELECT * FROM play_sessions
        WHERE memberId = :memberId
        ORDER BY id DESC
    """)
    fun getSessionsByMember(
        memberId: Int
    ): Flow<List<PlaySession>>

    @Query("""
        SELECT * FROM play_sessions
        ORDER BY id DESC
    """)
    fun getAllSessions(): Flow<List<PlaySession>>
}