package com.example.LivrariaAppV2.data.dao // Pag 2

import androidx.room.Dao // Pag 2.1
import androidx.room.Insert // Pag 2.1
import androidx.room.OnConflictStrategy // Pag 2.1
import androidx.room.Query // Pag 2.1
import com.example.LivrariaAppV2.data.model.User // Pag 2.1

@Dao // Pag 2.2
interface UserDao { // Pag 2.2
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Pag 2.3
    suspend fun insertUser(user: User) // Pag 2.3

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1") // Pag 2.4
    suspend fun getUserByUsername(username: String): User? // Pag 2.4
}