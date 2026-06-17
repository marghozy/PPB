package com.example.playarena.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: PlayArenaDatabase? = null

    fun getDatabase(
        context: Context
    ): PlayArenaDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                PlayArenaDatabase::class.java,
                "playarena_db"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}