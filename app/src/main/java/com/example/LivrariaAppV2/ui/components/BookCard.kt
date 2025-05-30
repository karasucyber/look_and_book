package com.example.LivrariaAppV2.ui // Pag 10

import androidx.compose.foundation.Image // Pag 10.1
import androidx.compose.foundation.clickable // Pag 10.1
import androidx.compose.foundation.layout.Column // Pag 10.1
import androidx.compose.foundation.layout.Row // Pag 10.1
import androidx.compose.foundation.layout.Spacer // Pag 10.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 10.1
import androidx.compose.foundation.layout.height // Pag 10.1
import androidx.compose.foundation.layout.padding // Pag 10.1
import androidx.compose.foundation.layout.size // Pag 10.1
import androidx.compose.foundation.layout.width // Pag 10.1
import androidx.compose.material.icons.Icons // Pag 10.1
import androidx.compose.material.icons.filled.Favorite // Pag 10.1
import androidx.compose.material.icons.filled.FavoriteBorder // Pag 10.1
import androidx.compose.material.icons.filled.Description // Pag 10.1
import androidx.compose.material3.Card // Pag 10.1
import androidx.compose.material3.CardDefaults // Pag 10.1
import androidx.compose.material3.Icon // Pag 10.1
import androidx.compose.material3.IconButton // Pag 10.1
import androidx.compose.material3.MaterialTheme // Pag 10.1
import androidx.compose.material3.Text // Pag 10.1
import androidx.compose.runtime.Composable // Pag 10.1
import androidx.compose.ui.Modifier // Pag 10.1
import androidx.compose.ui.layout.ContentScale // Pag 10.1
import androidx.compose.ui.unit.dp // Pag 10.1
import coil.compose.rememberAsyncImagePainter // Pag 10.1
import com.example.LivrariaAppV2.data.model.Book // Pag 10.1
import androidx.compose.ui.text.font.FontWeight // Pag 10.1

@Composable // Pag 10.2
fun BookCard( // Pag 10.2
    book: Book, // Pag 10.2.1
    onBookClick: (Int) -> Unit, // Pag 10.2.2
    onToggleFavorite: (Int) -> Unit, // Pag 10.2.3
    isFavorite: Boolean, // Pag 10.2.4
    modifier: Modifier = Modifier // Pag 10.2.5
) {
    Card( // Pag 10.3
        modifier = modifier // Pag 10.3.1
            .fillMaxWidth() // Pag 10.3.1
            .clickable { onBookClick(book.id) }, // Pag 10.3.2
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Pag 10.3.3
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant) // Pag 10.3.4
    ) {
        Row(modifier = Modifier.padding(8.dp)) { // Pag 10.4
            if (book.imageUrl.isNullOrEmpty()) { // Pag 10.5
                Icon( // Pag 10.5.1
                    imageVector = Icons.Filled.Description, // Pag 10.5.1
                    contentDescription = "Capa do Livro Indisponível", // Pag 10.5.1
                    modifier = Modifier.size(90.dp, 120.dp), // Pag 10.5.1
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f) // Pag 10.5.1
                )
            } else { // Pag 10.6
                Image( // Pag 10.6.1
                    painter = rememberAsyncImagePainter(model = book.imageUrl), // Pag 10.6.1
                    contentDescription = "Capa do Livro: ${book.title}", // Pag 10.6.1
                    modifier = Modifier.size(90.dp, 120.dp), // Pag 10.6.1
                    contentScale = ContentScale.Crop // Pag 10.6.1
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Pag 10.7

            Column( // Pag 10.8
                modifier = Modifier // Pag 10.8.1
                    .weight(1f) // Pag 10.8.1
                    .height(120.dp) // Pag 10.8.1
            ) {
                Text( // Pag 10.8.2
                    text = book.title, // Pag 10.8.2
                    style = MaterialTheme.typography.titleMedium, // Pag 10.8.2
                    fontWeight = FontWeight.Bold, // Pag 10.8.2
                    maxLines = 2 // Pag 10.8.2
                )
                Text( // Pag 10.8.3
                    text = "Autor: ${book.author}", // Pag 10.8.3
                    style = MaterialTheme.typography.bodyMedium, // Pag 10.8.3
                    maxLines = 1 // Pag 10.8.3
                )
                Text( // Pag 10.8.4
                    text = "Gênero: ${book.genre}", // Pag 10.8.4
                    style = MaterialTheme.typography.bodySmall, // Pag 10.8.4
                    maxLines = 1 // Pag 10.8.4
                )
                Text( // Pag 10.8.5
                    text = "Preço: R$ ${"%.2f".format(book.price)}", // Pag 10.8.5
                    style = MaterialTheme.typography.bodySmall // Pag 10.8.5
                )
                Text( // Pag 10.8.6
                    text = if (book.isActive) "Disponível" else "Inativo", // Pag 10.8.6
                    style = MaterialTheme.typography.bodySmall, // Pag 10.8.6
                    color = if (book.isActive) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.error // Pag 10.8.6
                )
            }
            IconButton( // Pag 10.9
                onClick = { onToggleFavorite(book.id) }, // Pag 10.9.1
                modifier = Modifier.padding(top = 4.dp) // Pag 10.9.1
            ) {
                Icon( // Pag 10.9.2
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder, // Pag 10.9.2
                    contentDescription = if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos", // Pag 10.9.2
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant // Pag 10.9.2
                )
            }
        }
    }
}