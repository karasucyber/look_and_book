package com.example.LivrariaAppV2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.LivrariaAppV2.data.local.FavoriteManager
import com.example.LivrariaAppV2.data.model.User // Importar o modelo User
import com.example.LivrariaAppV2.repository.BookRepository
import kotlinx.coroutines.flow.StateFlow

class BookViewModelFactory(
    private val bookRepository: BookRepository,
    private val favoriteManager: FavoriteManager,
    private val currentUserFlow: StateFlow<User?> // NOVO: Adiciona o fluxo do usuário atual
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            // Passa o currentUserFlow para o BookViewModel
            return BookViewModel(bookRepository, favoriteManager, currentUserFlow) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}