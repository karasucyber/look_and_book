package com.example.LivrariaAppV2.data.model // Pag 5

import androidx.room.Entity // Pag 5.1
import androidx.room.PrimaryKey // Pag 5.1

@Entity(tableName = "users") // Pag 5.2
data class User( // Pag 5.2
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Pag 5.3
    val username: String, // Pag 5.4
    val passwordHash: String, // Pag 5.5
    val role: String = "user" // Pag 5.6
)