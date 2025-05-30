package com.example.LivrariaAppV2.ui // Pag 14

import android.widget.Toast // Pag 14.1
import androidx.compose.foundation.layout.Arrangement // Pag 14.1
import androidx.compose.foundation.layout.Column // Pag 14.1
import androidx.compose.foundation.layout.Row // Pag 14.1
import androidx.compose.foundation.layout.Spacer // Pag 14.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 14.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 14.1
import androidx.compose.foundation.layout.height // Pag 14.1
import androidx.compose.foundation.layout.padding // Pag 14.1
import androidx.compose.foundation.layout.size // Pag 14.1
import androidx.compose.foundation.rememberScrollState // Pag 14.1
import androidx.compose.foundation.text.KeyboardOptions // Pag 14.1
import androidx.compose.foundation.verticalScroll // Pag 14.1
import androidx.compose.material.icons.Icons // Pag 14.1
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Pag 14.1
import androidx.compose.material.icons.filled.Done // Pag 14.1
import androidx.compose.material.icons.filled.PowerOff // Pag 14.1
import androidx.compose.material.icons.filled.PowerSettingsNew // Pag 14.1
import androidx.compose.material3.Card // Pag 14.1
import androidx.compose.material3.CardDefaults // Pag 14.1
import androidx.compose.material3.ExperimentalMaterial3Api // Pag 14.1
import androidx.compose.material3.Icon // Pag 14.1
import androidx.compose.material3.IconButton // Pag 14.1
import androidx.compose.material3.MaterialTheme // Pag 14.1
import androidx.compose.material3.OutlinedTextField // Pag 14.1
import androidx.compose.material3.Scaffold // Pag 14.1
import androidx.compose.material3.Text // Pag 14.1
import androidx.compose.material3.TopAppBar // Pag 14.1
import androidx.compose.material3.TopAppBarDefaults // Pag 14.1
import androidx.compose.runtime.Composable // Pag 14.1
import androidx.compose.runtime.LaunchedEffect // Pag 14.1
import androidx.compose.runtime.collectAsState // Pag 14.1
import androidx.compose.runtime.getValue // Pag 14.1
import androidx.compose.runtime.mutableDoubleStateOf // Pag 14.1
import androidx.compose.runtime.mutableIntStateOf // Pag 14.1
import androidx.compose.runtime.mutableStateOf // Pag 14.1
import androidx.compose.runtime.remember // Pag 14.1
import androidx.compose.runtime.setValue // Pag 14.1
import androidx.compose.ui.Alignment // Pag 14.1
import androidx.compose.ui.Modifier // Pag 14.1
import androidx.compose.ui.layout.ContentScale // Pag 14.1
import androidx.compose.ui.platform.LocalContext // Pag 14.1
import androidx.compose.ui.text.input.KeyboardType // Pag 14.1
import androidx.compose.ui.unit.dp // Pag 14.1
import coil.compose.AsyncImage // Pag 14.1
import com.example.LivrariaAppV2.data.model.Book // Pag 14.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 14.1

@OptIn(ExperimentalMaterial3Api::class) // Pag 14.2
@Composable // Pag 14.2
fun EditBookScreen( // Pag 14.2
    bookId: Int, // Pag 14.2.1
    bookViewModel: BookViewModel, // Pag 14.2.2
    onBack: () -> Unit, // Pag 14.2.3
    onBookDeleted: () -> Unit, // Pag 14.2.4
    isAdmin: Boolean // Pag 14.2.5
) {
    val context = LocalContext.current // Pag 14.3
    val bookState by bookViewModel.getBookById(bookId).collectAsState(initial = null) // Pag 14.4

    var title by remember { mutableStateOf("") } // Pag 14.5.1
    var author by remember { mutableStateOf("") } // Pag 14.5.2
    var description by remember { mutableStateOf("") } // Pag 14.5.3
    var price by remember { mutableDoubleStateOf(0.0) } // Pag 14.5.4
    var quantity by remember { mutableIntStateOf(0) } // Pag 14.5.5
    var imageUrl by remember { mutableStateOf("") } // Pag 14.5.6
    var isActive by remember { mutableStateOf(true) } // Pag 14.5.7

    LaunchedEffect(bookState) { // Pag 14.6
        bookState?.let { book -> // Pag 14.6.1
            title = book.title // Pag 14.6.1.1
            author = book.author // Pag 14.6.1.2
            description = book.description ?: "" // Pag 14.6.1.3
            price = book.price // Pag 14.6.1.4
            quantity = book.quantity // Pag 14.6.1.5
            imageUrl = book.imageUrl ?: "" // Pag 14.6.1.6
            isActive = book.isActive // Pag 14.6.1.7
        }
    }

    Scaffold( // Pag 14.7
        topBar = { // Pag 14.7.1
            TopAppBar( // Pag 14.7.1.1
                title = { Text("Detalhes do Livro") }, // Pag 14.7.1.2
                navigationIcon = { // Pag 14.7.1.3
                    IconButton(onClick = onBack) { // Pag 14.7.1.3.1
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar") // Pag 14.7.1.3.2
                    }
                },
                actions = { // Pag 14.7.1.4
                    if (isAdmin) { // Pag 14.7.1.4.1
                        IconButton(onClick = { // Pag 14.7.1.4.2
                            val currentBook = bookState // Pag 14.7.1.4.2
                            if (currentBook != null) { // Pag 14.7.1.4.2.1
                                val updatedBook = currentBook.copy( // Pag 14.7.1.4.2.2
                                    title = title,
                                    author = author,
                                    description = description.ifBlank { null },
                                    price = price,
                                    quantity = quantity,
                                    imageUrl = imageUrl.ifBlank { null },
                                    isActive = isActive
                                )
                                bookViewModel.updateBook(updatedBook) // Pag 14.7.1.4.2.3
                                Toast.makeText(context, "Livro atualizado!", Toast.LENGTH_SHORT).show() // Pag 14.7.1.4.2.4
                                onBack() // Pag 14.7.1.4.2.5
                            }
                        }) {
                            Icon(Icons.Default.Done, "Salvar Livro") // Pag 14.7.1.4.3
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Pag 14.7.1.5
                    containerColor = MaterialTheme.colorScheme.primary, // Pag 14.7.1.5
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 14.7.1.5
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 14.7.1.5
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary // Pag 14.7.1.5
                )
            )
        }
    ) { paddingValues -> // Pag 14.8
        Column( // Pag 14.8.1
            modifier = Modifier // Pag 14.8.1.1
                .fillMaxSize() // Pag 14.8.1.1
                .padding(paddingValues) // Pag 14.8.1.1
                .padding(16.dp) // Pag 14.8.1.1
                .verticalScroll(rememberScrollState()), // Pag 14.8.1.1
            verticalArrangement = Arrangement.spacedBy(8.dp) // Pag 14.8.1.2
        ) {
            bookState?.imageUrl?.let { url -> // Pag 14.8.2
                Card( // Pag 14.8.2.1
                    modifier = Modifier // Pag 14.8.2.1
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    AsyncImage( // Pag 14.8.2.2
                        model = url, // Pag 14.8.2.2
                        contentDescription = "Capa do Livro", // Pag 14.8.2.2
                        modifier = Modifier // Pag 14.8.2.2
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Fit, // Pag 14.8.2.2
                        alignment = Alignment.Center // Pag 14.8.2.2
                    )
                }
            }

            OutlinedTextField( // Pag 14.8.3
                value = title, // Pag 14.8.3
                onValueChange = { title = it }, // Pag 14.8.3
                label = { Text("Título") }, // Pag 14.8.3
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.3
                readOnly = !isAdmin // Pag 14.8.3
            )
            OutlinedTextField( // Pag 14.8.4
                value = author, // Pag 14.8.4
                onValueChange = { author = it }, // Pag 14.8.4
                label = { Text("Autor") }, // Pag 14.8.4
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.4
                readOnly = !isAdmin // Pag 14.8.4
            )
            OutlinedTextField( // Pag 14.8.5
                value = description, // Pag 14.8.5
                onValueChange = { description = it }, // Pag 14.8.5
                label = { Text("Descrição") }, // Pag 14.8.5
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.5
                readOnly = !isAdmin // Pag 14.8.5
            )
            OutlinedTextField( // Pag 14.8.6
                value = if (price == 0.0 && bookState?.price == 0.0) "" else price.toString(), // Pag 14.8.6
                onValueChange = { newValue -> // Pag 14.8.6
                    price = newValue.toDoubleOrNull() ?: 0.0 // Pag 14.8.6
                },
                label = { Text("Preço") }, // Pag 14.8.6
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Pag 14.8.6
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.6
                readOnly = !isAdmin // Pag 14.8.6
            )
            OutlinedTextField( // Pag 14.8.7
                value = if (quantity == 0 && bookState?.quantity == 0) "" else quantity.toString(), // Pag 14.8.7
                onValueChange = { newValue -> // Pag 14.8.7
                    quantity = newValue.toIntOrNull() ?: 0 // Pag 14.8.7
                },
                label = { Text("Quantidade") }, // Pag 14.8.7
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Pag 14.8.7
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.7
                readOnly = !isAdmin // Pag 14.8.7
            )
            OutlinedTextField( // Pag 14.8.8
                value = imageUrl, // Pag 14.8.8
                onValueChange = { imageUrl = it }, // Pag 14.8.8
                label = { Text("URL da Imagem") }, // Pag 14.8.8
                modifier = Modifier.fillMaxWidth(), // Pag 14.8.8
                readOnly = !isAdmin // Pag 14.8.8
            )

            if (isAdmin) { // Pag 14.8.9
                Row( // Pag 14.8.9.1
                    modifier = Modifier.fillMaxWidth(), // Pag 14.8.9.1
                    verticalAlignment = Alignment.CenterVertically, // Pag 14.8.9.1
                    horizontalArrangement = Arrangement.SpaceBetween // Pag 14.8.9.1
                ) {
                    Text( // Pag 14.8.9.2
                        text = "Status: ${if (isActive) "Ativo" else "Inativo"}", // Pag 14.8.9.2
                        style = MaterialTheme.typography.bodyLarge, // Pag 14.8.9.2
                        modifier = Modifier.weight(1f) // Pag 14.8.9.2
                    )
                    IconButton( // Pag 14.8.9.3
                        onClick = { // Pag 14.8.9.3.1
                            val bookToUpdate = bookState // Pag 14.8.9.3.1
                            if (bookToUpdate != null) { // Pag 14.8.9.3.1.1
                                if (isActive) { // Pag 14.8.9.3.1.2
                                    bookViewModel.deactivateBook(bookToUpdate.id) // Pag 14.8.9.3.1.2
                                    Toast.makeText(context, "Livro desativado!", Toast.LENGTH_SHORT).show() // Pag 14.8.9.3.1.2
                                } else { // Pag 14.8.9.3.1.3
                                    bookViewModel.activateBook(bookToUpdate.id) // Pag 14.8.9.3.1.3
                                    Toast.makeText(context, "Livro ativado!", Toast.LENGTH_SHORT).show() // Pag 14.8.9.3.1.3
                                }
                                isActive = !isActive // Pag 14.8.9.3.1.4
                                onBookDeleted() // Pag 14.8.9.3.1.5
                            }
                        },
                        modifier = Modifier.size(48.dp) // Pag 14.8.9.3
                    ) {
                        Icon( // Pag 14.8.9.4
                            imageVector = if (isActive) Icons.Default.PowerSettingsNew else Icons.Default.PowerOff, // Pag 14.8.9.4
                            contentDescription = if (isActive) "Desativar Livro" else "Ativar Livro", // Pag 14.8.9.4
                            tint = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error // Pag 14.8.9.4
                        )
                    }
                }
            } else { // Pag 14.8.10
                Text( // Pag 14.8.10.1
                    text = "Status: ${if (isActive) "Ativo" else "Inativo"}", // Pag 14.8.10.1
                    style = MaterialTheme.typography.bodyLarge, // Pag 14.8.10.1
                    modifier = Modifier.padding(vertical = 8.dp) // Pag 14.8.10.1
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Pag 14.9
        }
    }
}