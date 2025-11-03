package com.voxtype.keyboard.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "snippets",
    indices = [
        Index(value = ["trigger"], unique = true),
        Index(value = ["category"]),
        Index(value = ["usageCount"])
    ]
)
data class Snippet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trigger: String,
    val content: String,
    val category: String = "General",
    val usageCount: Int = 0,
    val createdDate: Date = Date()
)