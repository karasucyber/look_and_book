package com.example.LivrariaAppV2.ui // Pag 19

import androidx.compose.foundation.layout.fillMaxWidth // Pag 19.1
import androidx.compose.foundation.layout.height // Pag 19.1
import androidx.compose.material.icons.Icons // Pag 19.1
import androidx.compose.material.icons.filled.Close // Pag 19.1
import androidx.compose.material.icons.filled.Search // Pag 19.1
import androidx.compose.material3.Icon // Pag 19.1
import androidx.compose.material3.IconButton // Pag 19.1
import androidx.compose.material3.MaterialTheme // Pag 19.1
import androidx.compose.material3.OutlinedTextField // Pag 19.1
import androidx.compose.material3.OutlinedTextFieldDefaults // Pag 19.1
import androidx.compose.material3.Text // Pag 19.1
import androidx.compose.runtime.Composable // Pag 19.1
import androidx.compose.ui.Modifier // Pag 19.1
import androidx.compose.ui.unit.dp // Pag 19.1

@Composable // Pag 19.2
fun SearchBar( // Pag 19.2
    query: String, // Pag 19.2.1
    onQueryChange: (String) -> Unit, // Pag 19.2.2
    modifier: Modifier = Modifier // Pag 19.2.3
) {
    OutlinedTextField( // Pag 19.3
        value = query, // Pag 19.3.1
        onValueChange = onQueryChange, // Pag 19.3.2
        modifier = modifier // Pag 19.3.3
            .fillMaxWidth() // Pag 19.3.3
            .height(56.dp), // Pag 19.3.3
        placeholder = { Text("Buscar livros por título ou autor...") }, // Pag 19.3.4
        leadingIcon = { // Pag 19.3.5
            Icon(Icons.Default.Search, contentDescription = "Ícone de Busca") // Pag 19.3.5.1
        },
        trailingIcon = { // Pag 19.3.6
            if (query.isNotEmpty()) { // Pag 19.3.6.1
                IconButton(onClick = { onQueryChange("") }) { // Pag 19.3.6.2
                    Icon(Icons.Default.Close, contentDescription = "Limpar Busca") // Pag 19.3.6.3
                }
            }
        },
        singleLine = true, // Pag 19.3.7
        colors = OutlinedTextFieldDefaults.colors( // Pag 19.3.8
            focusedBorderColor = MaterialTheme.colorScheme.primary, // Pag 19.3.8
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Pag 19.3.8
            focusedLabelColor = MaterialTheme.colorScheme.primary, // Pag 19.3.8
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f) // Pag 19.3.8
        )
    )
}