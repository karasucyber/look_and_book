package com.example.LivrariaAppV2.ui // Pag 15

import androidx.compose.foundation.layout.Arrangement // Pag 15.1
import androidx.compose.foundation.layout.Column // Pag 15.1
import androidx.compose.foundation.layout.PaddingValues // Pag 15.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 15.1
import androidx.compose.foundation.layout.padding // Pag 15.1
import androidx.compose.foundation.lazy.LazyColumn // Pag 15.1
import androidx.compose.foundation.lazy.items // Pag 15.1
import androidx.compose.material.icons.Icons // Pag 15.1
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Pag 15.1
import androidx.compose.material3.ExperimentalMaterial3Api // Pag 15.1
import androidx.compose.material3.Icon // Pag 15.1
import androidx.compose.material3.IconButton // Pag 15.1
import androidx.compose.material3.MaterialTheme // Pag 15.1
import androidx.compose.material3.Scaffold // Pag 15.1
import androidx.compose.material3.Text // Pag 15.1
import androidx.compose.material3.TopAppBar // Pag 15.1
import androidx.compose.material3.TopAppBarDefaults // Pag 15.1
import androidx.compose.runtime.Composable // Pag 15.1
import androidx.compose.runtime.collectAsState // Pag 15.1
import androidx.compose.runtime.getValue // Pag 15.1
import androidx.compose.ui.Modifier // Pag 15.1
import androidx.compose.ui.unit.dp // Pag 15.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 15.1

@OptIn(ExperimentalMaterial3Api::class) // Pag 15.2
@Composable // Pag 15.2
fun FavoriteBooksScreen( // Pag 15.2
    bookViewModel: BookViewModel, // Pag 15.2.1
    onBookClick: (Int) -> Unit, // Pag 15.2.2
    onBack: () -> Unit // Pag 15.2.3
) {
    val favoriteBooks by bookViewModel.favoriteBooks.collectAsState(initial = emptyList()) // Pag 15.3

    Scaffold( // Pag 15.4
        topBar = { // Pag 15.4.1
            TopAppBar( // Pag 15.4.1.1
                title = { Text("Meus Favoritos") }, // Pag 15.4.1.2
                navigationIcon = { // Pag 15.4.1.3
                    IconButton(onClick = onBack) { // Pag 15.4.1.3.1
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar") // Pag 15.4.1.3.2
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Pag 15.4.1.4
                    containerColor = MaterialTheme.colorScheme.primary, // Pag 15.4.1.4
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 15.4.1.4
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Pag 15.4.1.4
                )
            )
        }
    ) { paddingValues -> // Pag 15.5
        Column( // Pag 15.5.1
            modifier = Modifier // Pag 15.5.1.1
                .fillMaxSize() // Pag 15.5.1.1
                .padding(paddingValues) // Pag 15.5.1.1
        ) {
            if (favoriteBooks.isEmpty()) { // Pag 15.5.2
                Text( // Pag 15.5.2.1
                    text = "Você ainda não adicionou livros aos favoritos.", // Pag 15.5.2.1
                    modifier = Modifier.padding(16.dp) // Pag 15.5.2.1
                )
            } else { // Pag 15.5.3
                LazyColumn( // Pag 15.5.3.1
                    modifier = Modifier.fillMaxSize(), // Pag 15.5.3.1
                    contentPadding = PaddingValues(16.dp), // Pag 15.5.3.1
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Pag 15.5.3.1
                ) {
                    items(favoriteBooks) { book -> // Pag 15.5.3.2
                        BookCard( // Pag 15.5.3.2.1
                            book = book, // Pag 15.5.3.2.1
                            onBookClick = onBookClick, // Pag 15.5.3.2.1
                            onToggleFavorite = { // Pag 15.5.3.2.1
                                bookViewModel.toggleFavorite(book.id) // Pag 15.5.3.2.1
                            },
                            isFavorite = true // Pag 15.5.3.2.1
                        )
                    }
                }
            }
        }
    }
}