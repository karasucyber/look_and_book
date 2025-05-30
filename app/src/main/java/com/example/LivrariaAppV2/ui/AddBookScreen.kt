package com.example.LivrariaAppV2.ui // Pag 12

import android.widget.Toast // Pag 12.1
import androidx.compose.foundation.layout.Arrangement // Pag 12.1
import androidx.compose.foundation.layout.Column // Pag 12.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 12.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 12.1
import androidx.compose.foundation.layout.padding // Pag 12.1
import androidx.compose.foundation.text.KeyboardOptions // Pag 12.1
import androidx.compose.material.icons.Icons // Pag 12.1
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Pag 12.1
import androidx.compose.material3.Button // Pag 12.1
import androidx.compose.material3.ExperimentalMaterial3Api // Pag 12.1
import androidx.compose.material3.Icon // Pag 12.1
import androidx.compose.material3.IconButton // Pag 12.1
import androidx.compose.material3.MaterialTheme // Pag 12.1
import androidx.compose.material3.OutlinedTextField // Pag 12.1
import androidx.compose.material3.Scaffold // Pag 12.1
import androidx.compose.material3.Text // Pag 12.1
import androidx.compose.material3.TopAppBar // Pag 12.1
import androidx.compose.material3.TopAppBarDefaults // Pag 12.1
import androidx.compose.runtime.Composable // Pag 12.1
import androidx.compose.runtime.getValue // Pag 12.1
import androidx.compose.runtime.mutableDoubleStateOf // Pag 12.1
import androidx.compose.runtime.mutableIntStateOf // Pag 12.1
import androidx.compose.runtime.mutableStateOf // Pag 12.1
import androidx.compose.runtime.remember // Pag 12.1
import androidx.compose.runtime.setValue // Pag 12.1
import androidx.compose.ui.Modifier // Pag 12.1
import androidx.compose.ui.platform.LocalContext // Pag 12.1
import androidx.compose.ui.text.input.KeyboardType // Pag 12.1
import androidx.compose.ui.unit.dp // Pag 12.1
import com.example.LivrariaAppV2.data.model.Book // Pag 12.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 12.1

@OptIn(ExperimentalMaterial3Api::class) // Pag 12.2
@Composable // Pag 12.2
fun AddBookScreen( // Pag 12.2
    bookViewModel: BookViewModel, // Pag 12.2.1
    onBookAdded: () -> Unit, // Pag 12.2.2
    onBack: () -> Unit // Pag 12.2.3
) {
    val context = LocalContext.current // Pag 12.3

    var title by remember { mutableStateOf("") } // Pag 12.4.1
    var author by remember { mutableStateOf("") } // Pag 12.4.2
    var description by remember { mutableStateOf("") } // Pag 12.4.3
    var price by remember { mutableDoubleStateOf(0.0) } // Pag 12.4.4
    var quantity by remember { mutableIntStateOf(0) } // Pag 12.4.5
    var imageUrl by remember { mutableStateOf("") } // Pag 12.4.6
    var genre by remember { mutableStateOf("") } // Pag 12.4.7

    Scaffold( // Pag 12.5
        topBar = { // Pag 12.5.1
            TopAppBar( // Pag 12.5.1.1
                title = { Text("Adicionar Livro") }, // Pag 12.5.1.2
                navigationIcon = { // Pag 12.5.1.3
                    IconButton(onClick = onBack) { // Pag 12.5.1.3.1
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar") // Pag 12.5.1.3.2
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Pag 12.5.1.4
                    containerColor = MaterialTheme.colorScheme.primary, // Pag 12.5.1.4
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 12.5.1.4
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Pag 12.5.1.4
                )
            )
        }
    ) { paddingValues -> // Pag 12.6
        Column( // Pag 12.6.1
            modifier = Modifier // Pag 12.6.1.1
                .fillMaxSize() // Pag 12.6.1.1
                .padding(paddingValues) // Pag 12.6.1.1
                .padding(16.dp), // Pag 12.6.1.1
            verticalArrangement = Arrangement.spacedBy(8.dp) // Pag 12.6.1.2
        ) {
            OutlinedTextField( // Pag 12.6.2
                value = title, // Pag 12.6.2
                onValueChange = { title = it }, // Pag 12.6.2
                label = { Text("Título") }, // Pag 12.6.2
                modifier = Modifier.fillMaxWidth() // Pag 12.6.2
            )
            OutlinedTextField( // Pag 12.6.3
                value = author, // Pag 12.6.3
                onValueChange = { author = it }, // Pag 12.6.3
                label = { Text("Autor") }, // Pag 12.6.3
                modifier = Modifier.fillMaxWidth() // Pag 12.6.3
            )
            OutlinedTextField( // Pag 12.6.4
                value = description, // Pag 12.6.4
                onValueChange = { description = it }, // Pag 12.6.4
                label = { Text("Descrição (opcional)") }, // Pag 12.6.4
                modifier = Modifier.fillMaxWidth() // Pag 12.6.4
            )
            OutlinedTextField( // Pag 12.6.5
                value = if (price == 0.0) "" else price.toString(), // Pag 12.6.5
                onValueChange = { newValue -> // Pag 12.6.5
                    price = newValue.toDoubleOrNull() ?: 0.0 // Pag 12.6.5
                },
                label = { Text("Preço") }, // Pag 12.6.5
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Pag 12.6.5
                modifier = Modifier.fillMaxWidth() // Pag 12.6.5
            )
            OutlinedTextField( // Pag 12.6.6
                value = if (quantity == 0) "" else quantity.toString(), // Pag 12.6.6
                onValueChange = { newValue -> // Pag 12.6.6
                    quantity = newValue.toIntOrNull() ?: 0 // Pag 12.6.6
                },
                label = { Text("Quantidade") }, // Pag 12.6.6
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Pag 12.6.6
                modifier = Modifier.fillMaxWidth() // Pag 12.6.6
            )
            OutlinedTextField( // Pag 12.6.7
                value = imageUrl, // Pag 12.6.7
                onValueChange = { imageUrl = it }, // Pag 12.6.7
                label = { Text("URL da Imagem (opcional)") }, // Pag 12.6.7
                modifier = Modifier.fillMaxWidth() // Pag 12.6.7
            )
            OutlinedTextField( // Pag 12.6.8
                value = genre, // Pag 12.6.8
                onValueChange = { genre = it }, // Pag 12.6.8
                label = { Text("Gênero") }, // Pag 12.6.8
                modifier = Modifier.fillMaxWidth() // Pag 12.6.8
            )

            Button( // Pag 12.6.9
                onClick = { // Pag 12.6.9.1
                    if (title.isNotBlank() && author.isNotBlank() && price > 0 && quantity >= 0 && genre.isNotBlank()) { // Pag 12.6.9.2
                        val newBook = Book( // Pag 12.6.9.3
                            title = title,
                            author = author,
                            description = description.ifBlank { null },
                            price = price,
                            quantity = quantity,
                            imageUrl = imageUrl.ifBlank { null },
                            isActive = true,
                            genre = genre
                        )
                        bookViewModel.addBook(newBook) // Pag 12.6.9.4
                        Toast.makeText(context, "Livro adicionado com sucesso!", Toast.LENGTH_SHORT).show() // Pag 12.6.9.5
                        onBookAdded() // Pag 12.6.9.6
                    } else { // Pag 12.6.9.7
                        Toast.makeText(context, "Preencha todos os campos obrigatórios (Título, Autor, Preço > 0, Quantidade >= 0, Gênero)", Toast.LENGTH_LONG).show() // Pag 12.6.9.7
                    }
                },
                modifier = Modifier.fillMaxWidth() // Pag 12.6.9
            ) {
                Text("Adicionar Livro") // Pag 12.6.9.8
            }
        }
    }
}