package com.example.LivrariaAppV2.ui.components // Pag 11

import androidx.compose.material3.AlertDialog // Pag 11.1
import androidx.compose.material3.Text // Pag 11.1
import androidx.compose.material3.TextButton // Pag 11.1
import androidx.compose.runtime.Composable // Pag 11.1

@Composable // Pag 11.2
fun ErrorDialog( // Pag 11.2
    message: String, // Pag 11.2.1
    onDismiss: () -> Unit // Pag 11.2.2
) {
    AlertDialog( // Pag 11.3
        onDismissRequest = onDismiss, // Pag 11.3.1
        title = { Text("Erro") }, // Pag 11.3.2
        text = { Text(message) }, // Pag 11.3.3
        confirmButton = { // Pag 11.3.4
            TextButton(onClick = onDismiss) { // Pag 11.3.4.1
                Text("OK") // Pag 11.3.4.2
            }
        }
    )
}