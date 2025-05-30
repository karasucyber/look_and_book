package com.example.LivrariaAppV2.viewmodel // Pag 22

import androidx.lifecycle.ViewModel // Pag 22.1
import androidx.lifecycle.viewModelScope // Pag 22.1
import com.example.LivrariaAppV2.data.local.FavoriteManager // Pag 22.1
import com.example.LivrariaAppV2.data.model.Book // Pag 22.1
import com.example.LivrariaAppV2.data.model.User // Pag 22.1
import com.example.LivrariaAppV2.repository.BookRepository // Pag 22.1
import kotlinx.coroutines.flow.MutableStateFlow // Pag 22.1
import kotlinx.coroutines.flow.StateFlow // Pag 22.1
import kotlinx.coroutines.flow.asStateFlow // Pag 22.1
import kotlinx.coroutines.flow.combine // Pag 22.1
import kotlinx.coroutines.flow.firstOrNull // Pag 22.1
import kotlinx.coroutines.flow.SharingStarted // Pag 22.1
import kotlinx.coroutines.flow.stateIn // Pag 22.1
import kotlinx.coroutines.flow.flatMapLatest // Pag 22.1
import kotlinx.coroutines.flow.flowOf // Pag 22.1
import kotlinx.coroutines.launch // Pag 22.1

class BookViewModel( // Pag 22.2
    private val bookRepository: BookRepository, // Pag 22.2.1
    private val favoriteManager: FavoriteManager, // Pag 22.2.2
    private val currentUserFlow: StateFlow<User?> // Pag 22.2.3
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("") // Pag 22.3
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow() // Pag 22.4

    private val _allBooks = bookRepository.getAllBooks() // Pag 22.5

    val favoriteBookIds: StateFlow<Set<Int>> = currentUserFlow // Pag 22.6
        .flatMapLatest { currentUser -> // Pag 22.6.1
            if (currentUser != null) { // Pag 22.6.2
                favoriteManager.getFavoriteBookIds(currentUser.id.toString()) // Pag 22.6.2.1
            } else { // Pag 22.6.3
                flowOf(emptySet()) // Pag 22.6.3.1
            }
        }
        .stateIn( // Pag 22.6.4
            viewModelScope, // Pag 22.6.4
            SharingStarted.WhileSubscribed(5000), // Pag 22.6.4
            emptySet() // Pag 22.6.4
        )

    val books: StateFlow<List<Book>> = combine( // Pag 22.7
        _allBooks, // Pag 22.7.1
        _searchQuery, // Pag 22.7.2
        currentUserFlow // Pag 22.7.3
    ) { allBooks, query, currentUser -> // Pag 22.7.4
        val isAdmin = currentUser?.role == "admin" // Pag 22.7.4.1
        val filteredBySearch = if (query.isBlank()) { // Pag 22.7.4.2
            allBooks // Pag 22.7.4.2
        } else { // Pag 22.7.4.3
            allBooks.filter { // Pag 22.7.4.3.1
                it.title.contains(query, ignoreCase = true) || // Pag 22.7.4.3.1
                        it.author.contains(query, ignoreCase = true) // Pag 22.7.4.3.1
            }
        }

        if (isAdmin) { // Pag 22.7.4.4
            filteredBySearch // Pag 22.7.4.4
        } else { // Pag 22.7.4.5
            filteredBySearch.filter { it.isActive } // Pag 22.7.4.5
        }
    }.stateIn( // Pag 22.7.5
        viewModelScope, // Pag 22.7.5
        SharingStarted.WhileSubscribed(5000), // Pag 22.7.5
        emptyList() // Pag 22.7.5
    )

    val favoriteBooks: StateFlow<List<Book>> = combine( // Pag 22.8
        _allBooks, // Pag 22.8.1
        favoriteBookIds, // Pag 22.8.2
        currentUserFlow // Pag 22.8.3
    ) { allBooks, favoriteIds, currentUser -> // Pag 22.8.4
        val isAdmin = currentUser?.role == "admin" // Pag 22.8.4.1
        val filteredFavorites = allBooks.filter { it.id in favoriteIds } // Pag 22.8.4.2

        if (isAdmin) { // Pag 22.8.4.3
            filteredFavorites // Pag 22.8.4.3
        } else { // Pag 22.8.4.4
            filteredFavorites.filter { it.isActive } // Pag 22.8.4.4
        }
    }.stateIn( // Pag 22.8.5
        viewModelScope, // Pag 22.8.5
        SharingStarted.WhileSubscribed(5000), // Pag 22.8.5
        emptyList() // Pag 22.8.5
    )

    fun getBookById(bookId: Int) = bookRepository.getBookById(bookId) // Pag 22.9

    fun addBook(book: Book) { // Pag 22.10
        viewModelScope.launch { // Pag 22.10.1
            bookRepository.insertBook(book) // Pag 22.10.2
        }
    }

    fun updateBook(book: Book) { // Pag 22.11
        viewModelScope.launch { // Pag 22.11.1
            bookRepository.updateBook(book) // Pag 22.11.2
        }
    }

    fun deactivateBook(bookId: Int) { // Pag 22.12
        viewModelScope.launch { // Pag 22.12.1
            bookRepository.deactivateBook(bookId) // Pag 22.12.2
        }
    }

    fun activateBook(bookId: Int) { // Pag 22.13
        viewModelScope.launch { // Pag 22.13.1
            bookRepository.activateBook(bookId) // Pag 22.13.2
        }
    }

    fun setSearchQuery(query: String) { // Pag 22.14
        _searchQuery.value = query // Pag 22.14.1
    }

    fun toggleFavorite(bookId: Int) { // Pag 22.15
        viewModelScope.launch { // Pag 22.15.1
            val currentUser = currentUserFlow.firstOrNull() // Pag 22.15.2
            if (currentUser != null) { // Pag 22.15.3
                val currentFavorites = favoriteBookIds.value // Pag 22.15.3.1
                val userId = currentUser.id.toString() // Pag 22.15.3.2

                if (currentFavorites.contains(bookId)) { // Pag 22.15.3.3
                    favoriteManager.removeFavoriteBook(userId, bookId) // Pag 22.15.3.3.1
                } else { // Pag 22.15.3.4
                    favoriteManager.addFavoriteBook(userId, bookId) // Pag 22.15.3.4.1
                }
            } else {
                // Opcional: Mostrar uma mensagem ao usuário se ele não estiver logado
            }
        }
    }
}