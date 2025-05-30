package com.example.LivrariaAppV2.ui // Pag 18

import android.widget.Toast // Pag 18.1
import androidx.compose.foundation.layout.Arrangement // Pag 18.1
import androidx.compose.foundation.layout.Column // Pag 18.1
import androidx.compose.foundation.layout.Spacer // Pag 18.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 18.1
import androidx.compose.foundation.layout.fillMaxWidth // Pag 18.1
import androidx.compose.foundation.layout.height // Pag 18.1
import androidx.compose.foundation.layout.padding // Pag 18.1
import androidx.compose.material.icons.Icons // Pag 18.1
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Pag 18.1
import androidx.compose.material3.Button // Pag 18.1
import androidx.compose.material3.ExperimentalMaterial3Api // Pag 18.1
import androidx.compose.material3.Icon // Pag 18.1
import androidx.compose.material3.IconButton // Pag 18.1
import androidx.compose.material3.MaterialTheme // Pag 18.1
import androidx.compose.material3.OutlinedTextField // Pag 18.1
import androidx.compose.material3.Scaffold // Pag 18.1
import androidx.compose.material3.Text // Pag 18.1
import androidx.compose.material3.TopAppBar // Pag 18.1
import androidx.compose.material3.TopAppBarDefaults // Pag 18.1
import androidx.compose.runtime.Composable // Pag 18.1
import androidx.compose.runtime.LaunchedEffect // Pag 18.1
import androidx.compose.runtime.collectAsState // Pag 18.1
import androidx.compose.runtime.getValue // Pag 18.1
import androidx.compose.runtime.mutableStateOf // Pag 18.1
import androidx.compose.runtime.remember // Pag 18.1
import androidx.compose.runtime.setValue // Pag 18.1
import androidx.compose.ui.Alignment // Pag 18.1
import androidx.compose.ui.Modifier // Pag 18.1
import androidx.compose.ui.platform.LocalContext // Pag 18.1
import androidx.compose.ui.text.input.PasswordVisualTransformation // Pag 18.1
import androidx.compose.ui.tooling.preview.Preview // Pag 18.1
import androidx.compose.ui.unit.dp // Pag 18.1
import androidx.lifecycle.viewmodel.compose.viewModel // Pag 18.1
import com.example.LivrariaAppV2.ui.theme.LivrariaAppV2Theme // Pag 18.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModel // Pag 18.1

@OptIn(ExperimentalMaterial3Api::class) // Pag 18.2
@Composable // Pag 18.2
fun RegisterScreen( // Pag 18.2
    authViewModel: AuthViewModel = viewModel(), // Pag 18.2.1
    onRegistrationSuccess: () -> Unit, // Pag 18.2.2
    onBack: () -> Unit // Pag 18.2.3
) {
    var username by remember { mutableStateOf("") } // Pag 18.3
    var password by remember { mutableStateOf("") } // Pag 18.4
    var confirmPassword by remember { mutableStateOf("") } // Pag 18.5
    val registrationError by authViewModel.registrationError.collectAsState() // Pag 18.6
    val context = LocalContext.current // Pag 18.7

    LaunchedEffect(registrationError) { // Pag 18.8
        registrationError?.let { errorMsg -> // Pag 18.8.1
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show() // Pag 18.8.1
            authViewModel.clearRegistrationError() // Pag 18.8.1
        }
    }

    Scaffold( // Pag 18.9
        topBar = { // Pag 18.9.1
            TopAppBar( // Pag 18.9.1.1
                title = { Text("Criar Nova Conta") }, // Pag 18.9.1.2
                navigationIcon = { // Pag 18.9.1.3
                    IconButton(onClick = onBack) { // Pag 18.9.1.3.1
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar") // Pag 18.9.1.3.2
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Pag 18.9.1.4
                    containerColor = MaterialTheme.colorScheme.primary, // Pag 18.9.1.4
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Pag 18.9.1.4
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Pag 18.9.1.4
                )
            )
        }
    ) { paddingValues -> // Pag 18.10
        Column( // Pag 18.10.1
            modifier = Modifier // Pag 18.10.1.1
                .fillMaxSize() // Pag 18.10.1.1
                .padding(paddingValues) // Pag 18.10.1.1
                .padding(16.dp), // Pag 18.10.1.1
            verticalArrangement = Arrangement.Center, // Pag 18.10.1.2
            horizontalAlignment = Alignment.CenterHorizontally // Pag 18.10.1.3
        ) {
            Text( // Pag 18.10.2
                text = "Crie sua conta para começar!", // Pag 18.10.2
                style = MaterialTheme.typography.headlineSmall, // Pag 18.10.2
                modifier = Modifier.padding(bottom = 32.dp) // Pag 18.10.2
            )

            OutlinedTextField( // Pag 18.10.3
                value = username, // Pag 18.10.3
                onValueChange = { username = it }, // Pag 18.10.3
                label = { Text("Nome de Usuário") }, // Pag 18.10.3
                singleLine = true, // Pag 18.10.3
                modifier = Modifier.fillMaxWidth() // Pag 18.10.3
            )
            Spacer(modifier = Modifier.height(8.dp)) // Pag 18.10.4
            OutlinedTextField( // Pag 18.10.5
                value = password, // Pag 18.10.5
                onValueChange = { password = it }, // Pag 18.10.5
                label = { Text("Senha") }, // Pag 18.10.5
                visualTransformation = PasswordVisualTransformation(), // Pag 18.10.5
                singleLine = true, // Pag 18.10.5
                modifier = Modifier.fillMaxWidth() // Pag 18.10.5
            )
            Spacer(modifier = Modifier.height(8.dp)) // Pag 18.10.6
            OutlinedTextField( // Pag 18.10.7
                value = confirmPassword, // Pag 18.10.7
                onValueChange = { confirmPassword = it }, // Pag 18.10.7
                label = { Text("Confirmar Senha") }, // Pag 18.10.7
                visualTransformation = PasswordVisualTransformation(), // Pag 18.10.7
                singleLine = true, // Pag 18.10.7
                modifier = Modifier.fillMaxWidth() // Pag 18.10.7
            )
            Spacer(modifier = Modifier.height(16.dp)) // Pag 18.10.8

            Button( // Pag 18.10.9
                onClick = { // Pag 18.10.9.1
                    if (password == confirmPassword) { // Pag 18.10.9.2
                        authViewModel.register(username, password, onRegistrationSuccess) // Pag 18.10.9.2
                    } else { // Pag 18.10.9.3
                        Toast.makeText(context, "As senhas não coincidem!", Toast.LENGTH_SHORT).show() // Pag 18.10.9.3
                    }
                },
                modifier = Modifier.fillMaxWidth() // Pag 18.10.9
            ) {
                Text("Registrar") // Pag 18.10.9.4
            }
        }
    }
}

@Preview(showBackground = true) // Pag 18.11
@Composable // Pag 18.11
fun RegisterScreenPreview() { // Pag 18.11
    LivrariaAppV2Theme { // Pag 18.11.1
        RegisterScreen( // Pag 18.11.2
            onRegistrationSuccess = {}, // Pag 18.11.2.1
            onBack = {} // Pag 18.11.2.2
        )
    }
}