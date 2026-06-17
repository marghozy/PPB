package com.example.playarena.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "play_sessions")
data class PlaySession(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val memberId: Int,

    val duration: Int,

    val consoleType: String,

    val pointEarned: Int,

    val date: String
)