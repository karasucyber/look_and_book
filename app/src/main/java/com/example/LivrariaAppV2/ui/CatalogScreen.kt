package com.example.LivrariaAppV2.ui // Pag 13

import androidx.compose.foundation.layout.Arrangement // Pag 13.1
import androidx.compose.foundation.layout.Column // Pag 13.1
import androidx.compose.foundation.layout.PaddingValues // Pag 13.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 13.1
import androidx.compose.foundation.lazy.LazyColumn // Pag 13.1
import androidx.compose.foundation.lazy.items // Pag 13.1
import androidx.compose.material3.Text // Pag 13.1
import androidx.compose.runtime.Composable // Pag 13.1
import androidx.compose.runtime.collectAsState // Pag 13.1
import androidx.compose.runtime.getValue // Pag 13.1
import androidx.compose.ui.Modifier // Pag 13.1
import androidx.compose.ui.unit.dp // Pag 13.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 13.1

@Composable // Pag 13.2
fun CatalogScreen( // Pag 13.2
    bookViewModel: BookViewModel, // Pag 13.2.1
    onBookClick: (Int) -> Unit, // Pag 13.2.2
    paddingValues: PaddingValues // Pag 13.2.3
) {
    val books by bookViewModel.books.collectAsState(initial = emptyList()) // Pag 13.3
    val favoriteBookIds by bookViewModel.favoriteBookIds.collectAsState(initial = emptySet()) // Pag 13.4

    Column(modifier = Modifier.fillMaxSize()) { // Pag 13.5
        if (books.isEmpty()) { // Pag 13.6
            Text("Nenhum livro encontrado.") // Pag 13.6.1
        } else { // Pag 13.7
            LazyColumn( // Pag 13.7.1
                contentPadding = PaddingValues(16.dp), // Pag 13.7.1.1
                verticalArrangement = Arrangement.spacedBy(8.dp) // Pag 13.7.1.2
            ) {
                items(books) { book -> // Pag 13.7.2
                    val isFavorite = favoriteBookIds.contains(book.id) // Pag 13.7.2.1
                    BookCard( // Pag 13.7.2.2
                        book = book, // Pag 13.7.2.2.1
                        onBookClick = onBookClick, // Pag 13.7.2.2.2
                        onToggleFavorite = { bookId -> bookViewModel.toggleFavorite(bookId) }, // Pag 13.7.2.2.3
                        isFavorite = isFavorite // Pag 13.7.2.2.4
                    )
                }
            }
        }
    }
}