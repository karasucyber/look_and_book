package com.example.LivrariaAppV2.repository // Pag 9

import com.example.LivrariaAppV2.data.dao.BookDao // Pag 9.1
import com.example.LivrariaAppV2.data.model.Book // Pag 9.1
import kotlinx.coroutines.flow.Flow // Pag 9.1

class BookRepository(private val bookDao: BookDao) { // Pag 9.2

    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks() // Pag 9.3

    fun getBookById(bookId: Int): Flow<Book?> = bookDao.getBookById(bookId) // Pag 9.4

    suspend fun insertBook(book: Book) { // Pag 9.5
        bookDao.insertBook(book) // Pag 9.5
    }

    suspend fun updateBook(book: Book) { // Pag 9.6
        bookDao.updateBook(book) // Pag 9.6
    }

    suspend fun deactivateBook(bookId: Int) { // Pag 9.7
        bookDao.deactivateBook(bookId) // Pag 9.7
    }

    suspend fun activateBook(bookId: Int) { // Pag 9.8
        bookDao.activateBook(bookId) // Pag 9.8
    }

    fun searchBooks(query: String): Flow<List<Book>> = bookDao.searchBooks(query) // Pag 9.9
}