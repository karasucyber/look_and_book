package com.example.LivrariaAppV2.viewmodel // Pag 20

import androidx.lifecycle.ViewModel // Pag 20.1
import androidx.lifecycle.viewModelScope // Pag 20.1
import com.example.LivrariaAppV2.data.model.User // Pag 20.1
import com.example.LivrariaAppV2.repository.AuthRepository // Pag 20.1
import kotlinx.coroutines.flow.MutableStateFlow // Pag 20.1
import kotlinx.coroutines.flow.StateFlow // Pag 20.1
import kotlinx.coroutines.flow.asStateFlow // Pag 20.1
import kotlinx.coroutines.launch // Pag 20.1

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() { // Pag 20.2

    private val _currentUser = MutableStateFlow<User?>(null) // Pag 20.3
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow() // Pag 20.4

    private val _loginError = MutableStateFlow<String?>(null) // Pag 20.5
    val loginError: StateFlow<String?> = _loginError.asStateFlow() // Pag 20.6

    private val _registrationError = MutableStateFlow<String?>(null) // Pag 20.7
    val registrationError: StateFlow<String?> = _registrationError.asStateFlow() // Pag 20.8

    fun login(username: String, passwordPlain: String, onLoginSuccess: () -> Unit) { // Pag 20.9
        _loginError.value = null // Pag 20.9.1
        viewModelScope.launch { // Pag 20.9.2
            val user = authRepository.loginUser(username, passwordPlain) // Pag 20.9.3
            if (user != null) { // Pag 20.9.4
                _currentUser.value = user // Pag 20.9.4.1
                onLoginSuccess() // Pag 20.9.4.2
            } else { // Pag 20.9.5
                _loginError.value = "Nome de usuário ou senha incorretos." // Pag 20.9.5.1
            }
        }
    }

    fun register(username: String, passwordPlain: String, onRegistrationSuccess: () -> Unit) { // Pag 20.10
        _registrationError.value = null // Pag 20.10.1
        viewModelScope.launch { // Pag 20.10.2
            if (username.isBlank() || passwordPlain.isBlank()) { // Pag 20.10.3
                _registrationError.value = "Nome de usuário e senha não podem ser vazios." // Pag 20.10.3.1
                return@launch // Pag 20.10.3.2
            }
            if (passwordPlain.length < 6) { // Pag 20.10.4
                _registrationError.value = "A senha deve ter pelo menos 6 caracteres." // Pag 20.10.4.1
                return@launch // Pag 20.10.4.2
            }

            val success = authRepository.registerUser(username, passwordPlain) // Pag 20.10.5
            if (success) { // Pag 20.10.6
                // Após o registro bem-sucedido, loga o usuário automaticamente
                login(username, passwordPlain, onRegistrationSuccess) // Pag 20.10.6.1
            } else { // Pag 20.10.7
                _registrationError.value = "Nome de usuário já existe." // Pag 20.10.7.1
            }
        }
    }

    fun logout() { // Pag 20.11
        _currentUser.value = null // Pag 20.11.1
    }

    fun clearLoginError() { // Pag 20.12
        _loginError.value = null // Pag 20.12.1
    }

    fun clearRegistrationError() { // Pag 20.13
        _registrationError.value = null // Pag 20.13.1
    }
}