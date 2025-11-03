package com.voxtype.keyboard.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "dictionary_words",
    indices = [
        Index(value = ["word"], unique = true),
        Index(value = ["language"]),
        Index(value = ["frequency"])
    ]
)
data class DictionaryWord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String,
    val language: String = "en",
    val frequency: Int = 1,
    val createdDate: Date = Date(),
    val lastUsed: Date = Date()
)