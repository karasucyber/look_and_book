package com.example.LivrariaAppV2 // Pag 25

import android.os.Bundle // Pag 25.1
import androidx.activity.ComponentActivity // Pag 25.1
import androidx.activity.compose.setContent // Pag 25.1
import androidx.compose.foundation.layout.fillMaxSize // Pag 25.1
import androidx.compose.material3.MaterialTheme // Pag 25.1
import androidx.compose.material3.Surface // Pag 25.1
import androidx.compose.ui.Modifier // Pag 25.1
import androidx.navigation.compose.rememberNavController // Pag 25.1
import com.example.LivrariaAppV2.data.AppDatabase // Pag 25.1
import com.example.LivrariaAppV2.data.local.FavoriteManager // Pag 25.1
import com.example.LivrariaAppV2.navigation.AppNavHost // Pag 25.1
import com.example.LivrariaAppV2.repository.AuthRepository // Pag 25.1
import com.example.LivrariaAppV2.repository.BookRepository // Pag 25.1
import com.example.LivrariaAppV2.ui.theme.LivrariaAppV2Theme // Pag 25.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModel // Pag 25.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModelFactory // Pag 25.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 25.1
import com.example.LivrariaAppV2.viewmodel.BookViewModelFactory // Pag 25.1
import androidx.lifecycle.viewmodel.compose.viewModel // Pag 25.1

class MainActivity : ComponentActivity() { // Pag 25.2
    override fun onCreate(savedInstanceState: Bundle?) { // Pag 25.3
        super.onCreate(savedInstanceState) // Pag 25.3.1

        val database = AppDatabase.getDatabase(applicationContext) // Pag 25.4
        val userDao = database.userDao() // Pag 25.5
        val bookDao = database.bookDao() // Pag 25.6

        val authRepository = AuthRepository(userDao) // Pag 25.7
        val bookRepository = BookRepository(bookDao) // Pag 25.8
        val favoriteManager = FavoriteManager(applicationContext) // Pag 25.9

        val authViewModelFactory = AuthViewModelFactory(authRepository) // Pag 25.10


        setContent { // Pag 25.11
            LivrariaAppV2Theme { // Pag 25.11.1
                Surface( // Pag 25.11.2
                    modifier = Modifier.fillMaxSize(), // Pag 25.11.2.1
                    color = MaterialTheme.colorScheme.background // Pag 25.11.2.2
                ) {
                    val authViewModel: AuthViewModel = viewModel(factory = authViewModelFactory) // Pag 25.11.3

                    val bookViewModelFactory = BookViewModelFactory(bookRepository, favoriteManager, authViewModel.currentUser) // Pag 25.11.4
                    val bookViewModel: BookViewModel = viewModel(factory = bookViewModelFactory) // Pag 25.11.5


                    val navController = rememberNavController() // Pag 25.11.6
                    AppNavHost( // Pag 25.11.7
                        navController = navController, // Pag 25.11.7.1
                        authViewModel = authViewModel, // Pag 25.11.7.2
                        bookViewModel = bookViewModel // Pag 25.11.7.3
                    )
                }
            }
        }
    }
}