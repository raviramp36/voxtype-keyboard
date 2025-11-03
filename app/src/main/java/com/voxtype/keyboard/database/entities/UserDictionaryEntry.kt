package com.voxtype.keyboard.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "user_dictionary_entries",
    indices = [
        Index(value = ["word"], unique = true)
    ]
)
data class UserDictionaryEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String,
    val replacement: String,
    val createdDate: Date = Date()
)