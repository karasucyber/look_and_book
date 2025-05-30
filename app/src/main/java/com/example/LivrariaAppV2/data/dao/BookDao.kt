package com.example.LivrariaAppV2.data.dao // Pag 1

import androidx.room.Dao // Pag 1.1
import androidx.room.Insert // Pag 1.1
import androidx.room.OnConflictStrategy // Pag 1.1
import androidx.room.Query // Pag 1.1
import androidx.room.Update // Pag 1.1
import com.example.LivrariaAppV2.data.model.Book // Pag 1.1
import kotlinx.coroutines.flow.Flow // Pag 1.1

@Dao // Pag 1.2
interface BookDao { // Pag 1.2
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Pag 1.3
    suspend fun insertBook(book: Book) // Pag 1.3

    @Update // Pag 1.4
    suspend fun updateBook(book: Book) // Pag 1.4

    @Query("SELECT * FROM books WHERE id = :bookId") // Pag 1.5
    fun getBookById(bookId: Int): Flow<Book?> // Pag 1.5

    @Query("SELECT * FROM books ORDER BY title ASC") // Pag 1.6
    fun getAllBooks(): Flow<List<Book>> // Pag 1.6

    @Query("UPDATE books SET isActive = 0 WHERE id = :bookId") // Pag 1.7
    suspend fun deactivateBook(bookId: Int) // Pag 1.7

    @Query("UPDATE books SET isActive = 1 WHERE id = :bookId") // Pag 1.8
    suspend fun activateBook(bookId: Int) // Pag 1.8

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%' ORDER BY title ASC") // Pag 1.9
    fun searchBooks(query: String): Flow<List<Book>> // Pag 1.9
}