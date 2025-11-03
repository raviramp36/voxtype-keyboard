package com.voxtype.keyboard.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "user_statistics",
    indices = [
        Index(value = ["date"], unique = true)
    ]
)
data class UserStatistics(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String, // Format: "2024-11-02"
    val wordsTyped: Int = 0,
    val wpm: Float = 0f,
    val appsUsed: String = "", // Comma-separated list of app packages
    val languagesUsed: String = "" // Comma-separated list of languages
)