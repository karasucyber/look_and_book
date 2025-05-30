package com.example.LivrariaAppV2.viewmodel // Pag 21

import androidx.lifecycle.ViewModel // Pag 21.1
import androidx.lifecycle.ViewModelProvider // Pag 21.1
import com.example.LivrariaAppV2.repository.AuthRepository // Pag 21.1

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory { // Pag 21.2
    override fun <T : ViewModel> create(modelClass: Class<T>): T { // Pag 21.3
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) { // Pag 21.3.1
            @Suppress("UNCHECKED_CAST") // Pag 21.3.1
            return AuthViewModel(authRepository) as T // Pag 21.3.1
        }
        throw IllegalArgumentException("Unknown ViewModel class") // Pag 21.3.2
    }
}