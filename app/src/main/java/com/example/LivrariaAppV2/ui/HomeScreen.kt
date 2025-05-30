package com.example.LivrariaAppV2.ui // Pag 16

import android.widget.Toast // Pag 16.1
import androidx.compose.foundation.layout.Column // Pag 16.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 16.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 16.1
import androidx.compose.foundation.layout.padding // Pag 16.1
import androidx.compose.material.icons.Icons // Pag 16.1
import androidx.compose.material.icons.filled.Add // Pag 16.1
import androidx.compose.material.icons.filled.ExitToApp // Pag 16.1
import androidx.compose.material.icons.filled.Favorite // Pag 16.1
import androidx.compose.material3.ExperimentalMaterial3Api // Pag 16.1
import androidx.compose.material3.Icon // Pag 16.1
import androidx.compose.material3.IconButton // Pag 16.1
import androidx.compose.material3.MaterialTheme // Pag 16.1
import androidx.compose.material3.Scaffold // Pag 16.1
import androidx.compose.material3.Text // Pag 16.1
import androidx.compose.material3.TopAppBar // Pag 16.1
import androidx.compose.material3.TopAppBarDefaults // Pag 16.1
import androidx.compose.runtime.Composable // Pag 16.1
import androidx.compose.runtime.collectAsState // Pag 16.1
import androidx.compose.runtime.getValue // Pag 16.1
import androidx.compose.ui.Modifier // Pag 16.1
import androidx.compose.ui.unit.dp // Pag 16.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModel // Pag 16.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 16.1
import androidx.compose.foundation.layout.PaddingValues // Pag 16.1

@OptIn(ExperimentalMaterial3Api::class) // Pag 16.2
@Composable // Pag 16.2
fun HomeScreen( // Pag 16.2
    authViewModel: AuthViewModel, // Pag 16.2.1
    bookViewModel: BookViewModel, // Pag 16.2.2
    onNavigateToAddBook: () -> Unit, // Pag 16.2.3
    onBookClick: (Int) -> Unit, // Pag 16.2.4
    onLogout: () -> Unit, // Pag 16.2.5
    onNavigateToFavoriteBooks: () -> Unit // Pag 16.2.6
) {
    val currentUser by authViewModel.currentUser.collectAsState() // Pag 16.3
    val isAdmin = currentUser?.role == "admin" // Pag 16.4
    val searchQuery by bookViewModel.searchQuery.collectAsState() // Pag 16.5

    Scaffold( // Pag 16.6
        topBar = { // Pag 16.6.1
            TopAppBar( // Pag 16.6.1.1
                title = { Text("Livraria App") }, // Pag 16.6.1.2
                actions = { // Pag 16.6.1.3
                    IconButton(onClick = onNavigateToFavoriteBooks) { // Pag 16.6.1.3.1
                        Icon(Icons.Filled.Favorite, contentDescription = "Meus Favoritos") // Pag 16.6.1.3.2
                    }

                    if (isAdmin) { // Pag 16.6.1.3.3
                        IconButton(onClick = onNavigateToAddBook) { // Pag 16.6.1.3.4
                            Icon(Icons.Filled.Add, contentDescription = "Adicionar Livro") // Pag 16.6.1.3.5
                        }
                    }

                    IconButton(onClick = onLogout) { // Pag 16.6.1.3.6
                        Icon(Icons.Filled.ExitToApp, contentDescription = "Sair") // Pag 16.6.1.3.7
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Pag 16.6.1.4
                    containerColor = MaterialTheme.colorScheme.primary, // Pag 16.6.1.4
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 16.6.1.4
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary // Pag 16.6.1.4
                )
            )
        },
    ) { paddingValues -> // Pag 16.7
        Column( // Pag 16.7.1
            modifier = Modifier // Pag 16.7.1.1
                .fillMaxSize() // Pag 16.7.1.1
                .padding(paddingValues) // Pag 16.7.1.1
        ) {
            SearchBar( // Pag 16.7.2
                query = searchQuery, // Pag 16.7.2.1
                onQueryChange = { newQuery -> // Pag 16.7.2.2
                    bookViewModel.setSearchQuery(newQuery) // Pag 16.7.2.2.1
                },
                modifier = Modifier // Pag 16.7.2.3
                    .fillMaxWidth() // Pag 16.7.2.3.1
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Pag 16.7.2.3.2
            )
            CatalogScreen( // Pag 16.7.3
                bookViewModel = bookViewModel, // Pag 16.7.3.1
                onBookClick = onBookClick, // Pag 16.7.3.2
                paddingValues = PaddingValues(0.dp) // Pag 16.7.3.3
            )
        }
    }
}