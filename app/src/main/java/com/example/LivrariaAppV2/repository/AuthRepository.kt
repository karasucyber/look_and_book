package com.example.LivrariaAppV2.repository // Pag 8

import com.example.LivrariaAppV2.data.dao.UserDao // Pag 8.1
import com.example.LivrariaAppV2.data.model.User // Pag 8.1
import org.mindrot.jbcrypt.BCrypt // Pag 8.1

class AuthRepository(private val userDao: UserDao) { // Pag 8.2

    private fun hashPassword(password: String): String { // Pag 8.3
        return BCrypt.hashpw(password, BCrypt.gensalt(10)) // Pag 8.3
    }

    private fun checkPassword(password: String, hashedPassword: String): Boolean { // Pag 8.4
        return try { // Pag 8.4
            BCrypt.checkpw(password, hashedPassword) // Pag 8.4
        } catch (e: IllegalArgumentException) { // Pag 8.4
            false // Pag 8.4
        }
    }

    suspend fun registerUser(username: String, passwordPlain: String, role: String = "user"): Boolean { // Pag 8.5
        if (userDao.getUserByUsername(username) != null) { // Pag 8.5.1
            return false // Pag 8.5.1
        }
        val passwordHash = hashPassword(passwordPlain) // Pag 8.5.2
        val newUser = User(username = username, passwordHash = passwordHash, role = role) // Pag 8.5.3
        userDao.insertUser(newUser) // Pag 8.5.4
        return true // Pag 8.5.5
    }

    suspend fun loginUser(username: String, passwordPlain: String): User? { // Pag 8.6
        val user = userDao.getUserByUsername(username) // Pag 8.6.1
        return if (user != null && checkPassword(passwordPlain, user.passwordHash)) { // Pag 8.6.2
            user // Pag 8.6.2
        } else { // Pag 8.6.3
            null // Pag 8.6.3
        }
    }
}