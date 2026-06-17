package com.example.playarena.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playarena.data.dao.MemberDao
import com.example.playarena.data.dao.PlaySessionDao
import com.example.playarena.data.entity.Member
import com.example.playarena.data.entity.PlaySession

@Database(
    entities = [
        Member::class,
        PlaySession::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PlayArenaDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao

    abstract fun playSessionDao(): PlaySessionDao
}