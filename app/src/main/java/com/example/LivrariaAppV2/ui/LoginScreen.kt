package com.example.LivrariaAppV2.ui // Pag 17

import android.widget.Toast // Pag 17.1
import androidx.compose.foundation.layout.Arrangement // Pag 17.1
import androidx.compose.foundation.layout.Column // Pag 17.1
import androidx.compose.foundation.layout.Spacer // Pag 17.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 17.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 17.1
import androidx.compose.foundation.layout.height // Pag 17.1
import androidx.compose.foundation.layout.padding // Pag 17.1
import androidx.compose.material3.Button // Pag 17.1
import androidx.compose.material3.MaterialTheme // Pag 17.1
import androidx.compose.material3.OutlinedTextField // Pag 17.1
import androidx.compose.material3.Text // Pag 17.1
import androidx.compose.material3.TextButton // Pag 17.1
import androidx.compose.runtime.Composable // Pag 17.1
import androidx.compose.runtime.LaunchedEffect // Pag 17.1
import androidx.compose.runtime.collectAsState // Pag 17.1
import androidx.compose.runtime.getValue // Pag 17.1
import androidx.compose.runtime.mutableStateOf // Pag 17.1
import androidx.compose.runtime.remember // Pag 17.1
import androidx.compose.runtime.setValue // Pag 17.1
import androidx.compose.ui.Alignment // Pag 17.1
import androidx.compose.ui.Modifier // Pag 17.1
import androidx.compose.ui.platform.LocalContext // Pag 17.1
import androidx.compose.ui.text.input.PasswordVisualTransformation // Pag 17.1
import androidx.compose.ui.tooling.preview.Preview // Pag 17.1
import androidx.compose.ui.unit.dp // Pag 17.1
import androidx.lifecycle.viewmodel.compose.viewModel // Pag 17.1
import com.example.LivrariaAppV2.ui.theme.LivrariaAppV2Theme // Pag 17.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModel // Pag 17.1

@Composable // Pag 17.2
fun LoginScreen( // Pag 17.2
    authViewModel: AuthViewModel = viewModel(), // Pag 17.2.1
    onLoginSuccess: () -> Unit, // Pag 17.2.2
    onNavigateToRegister: () -> Unit // Pag 17.2.3
) {
    var username by remember { mutableStateOf("") } // Pag 17.3
    var password by remember { mutableStateOf("") } // Pag 17.4
    val loginError by authViewModel.loginError.collectAsState() // Pag 17.5
    val context = LocalContext.current // Pag 17.6

    LaunchedEffect(loginError) { // Pag 17.7
        loginError?.let { errorMsg -> // Pag 17.7.1
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show() // Pag 17.7.1
            authViewModel.clearLoginError() // Pag 17.7.1
        }
    }

    Column( // Pag 17.8
        modifier = Modifier // Pag 17.8.1
            .fillMaxSize() // Pag 17.8.1
            .padding(16.dp), // Pag 17.8.1
        verticalArrangement = Arrangement.Center, // Pag 17.8.2
        horizontalAlignment = Alignment.CenterHorizontally // Pag 17.8.3
    ) {
        Text( // Pag 17.8.4
            text = "Bem-vindo à Livraria!", // Pag 17.8.4
            style = MaterialTheme.typography.headlineMedium, // Pag 17.8.4
            modifier = Modifier.padding(bottom = 32.dp) // Pag 17.8.4
        )

        OutlinedTextField( // Pag 17.8.5
            value = username, // Pag 17.8.5
            onValueChange = { username = it }, // Pag 17.8.5
            label = { Text("Nome de Usuário") }, // Pag 17.8.5
            singleLine = true, // Pag 17.8.5
            modifier = Modifier.fillMaxWidth() // Pag 17.8.5
        )
        Spacer(modifier = Modifier.height(8.dp)) // Pag 17.8.6
        OutlinedTextField( // Pag 17.8.7
            value = password, // Pag 17.8.7
            onValueChange = { password = it }, // Pag 17.8.7
            label = { Text("Senha") }, // Pag 17.8.7
            visualTransformation = PasswordVisualTransformation(), // Pag 17.8.7
            singleLine = true, // Pag 17.8.7
            modifier = Modifier.fillMaxWidth() // Pag 17.8.7
        )
        Spacer(modifier = Modifier.height(16.dp)) // Pag 17.8.8

        Button( // Pag 17.8.9
            onClick = { // Pag 17.8.9.1
                authViewModel.login(username, password, onLoginSuccess) // Pag 17.8.9.1
            },
            modifier = Modifier.fillMaxWidth() // Pag 17.8.9
        ) {
            Text("Entrar") // Pag 17.8.9.2
        }
        Spacer(modifier = Modifier.height(8.dp)) // Pag 17.8.10
        TextButton( // Pag 17.8.11
            onClick = onNavigateToRegister, // Pag 17.8.11.1
            modifier = Modifier.fillMaxWidth() // Pag 17.8.11
        ) {
            Text("Não tem uma conta? Cadastre-se") // Pag 17.8.11.2
        }
    }
}

@Preview(showBackground = true) // Pag 17.9
@Composable // Pag 17.9
fun LoginScreenPreview() { // Pag 17.9
    LivrariaAppV2Theme { // Pag 17.9.1
        LoginScreen( // Pag 17.9.2
            onLoginSuccess = {}, // Pag 17.9.2.1
            onNavigateToRegister = {} // Pag 17.9.2.2
        )
    }
}