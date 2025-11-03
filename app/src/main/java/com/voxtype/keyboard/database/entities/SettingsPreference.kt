package com.voxtype.keyboard.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "settings_preferences",
    indices = [
        Index(value = ["key"], unique = true)
    ]
)
data class SettingsPreference(
    @PrimaryKey
    val key: String,
    val value: String
)