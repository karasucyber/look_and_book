package com.example.LivrariaAppV2.data.model // Pag 4

import androidx.room.Entity // Pag 4.1
import androidx.room.PrimaryKey // Pag 4.1

@Entity(tableName = "books") // Pag 4.2
data class Book( // Pag 4.2
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Pag 4.3
    val title: String, // Pag 4.4
    val author: String, // Pag 4.5
    val description: String?, // Pag 4.6
    val price: Double, // Pag 4.7
    val quantity: Int, // Pag 4.8
    val imageUrl: String?, // Pag 4.9
    val isActive: Boolean = true, // Pag 4.10
    val genre: String // Pag 4.11
)