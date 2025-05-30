package com.example.LivrariaAppV2.navigation // Pag 7

import androidx.compose.runtime.Composable // Pag 7.1
import androidx.compose.runtime.collectAsState // Pag 7.1
import androidx.compose.runtime.getValue // Pag 7.1
import androidx.navigation.NavHostController // Pag 7.1
import androidx.navigation.compose.NavHost // Pag 7.1
import androidx.navigation.compose.composable // Pag 7.1
import androidx.navigation.compose.rememberNavController // Pag 7.1
import com.example.LivrariaAppV2.ui.AddBookScreen // Pag 7.1
import com.example.LivrariaAppV2.ui.EditBookScreen // Pag 7.1
import com.example.LivrariaAppV2.ui.HomeScreen // Pag 7.1
import com.example.LivrariaAppV2.ui.LoginScreen // Pag 7.1
import com.example.LivrariaAppV2.ui.RegisterScreen // Pag 7.1
import com.example.LivrariaAppV2.viewmodel.AuthViewModel // Pag 7.1
import com.example.LivrariaAppV2.viewmodel.BookViewModel // Pag 7.1
import com.example.LivrariaAppV2.ui.FavoriteBooksScreen // Pag 7.1

object Routes { // Pag 7.2
    const val LOGIN = "login" // Pag 7.2.1
    const val REGISTER = "register" // Pag 7.2.2
    const val HOME = "home" // Pag 7.2.3
    const val ADDBOOK = "addBook" // Pag 7.2.4
    const val EDITBOOK = "editBook/{bookId}" // Pag 7.2.5
    const val FAVORITE_BOOKS = "favorite_books" // Pag 7.2.6
}

@Composable // Pag 7.3
fun AppNavHost( // Pag 7.3
    navController: NavHostController = rememberNavController(), // Pag 7.3.1
    authViewModel: AuthViewModel, // Pag 7.3.2
    bookViewModel: BookViewModel // Pag 7.3.3
) {
    val currentUser by authViewModel.currentUser.collectAsState() // Pag 7.4

    val startDestination = if (currentUser != null) Routes.HOME else Routes.LOGIN // Pag 7.5

    NavHost(navController = navController, startDestination = startDestination) { // Pag 7.6

        composable(Routes.LOGIN) { // Pag 7.7
            LoginScreen( // Pag 7.7.1
                authViewModel = authViewModel, // Pag 7.7.2
                onLoginSuccess = { // Pag 7.7.3
                    navController.navigate(Routes.HOME) { // Pag 7.7.3.1
                        popUpTo(Routes.LOGIN) { inclusive = true } // Pag 7.7.3.2
                    }
                },
                onNavigateToRegister = { // Pag 7.7.4
                    navController.navigate(Routes.REGISTER) // Pag 7.7.4.1
                }
            )
        }

        composable(Routes.REGISTER) { // Pag 7.8
            RegisterScreen( // Pag 7.8.1
                authViewModel = authViewModel, // Pag 7.8.2
                onRegistrationSuccess = { // Pag 7.8.3
                    navController.navigate(Routes.HOME) { // Pag 7.8.3.1
                        popUpTo(Routes.LOGIN) { inclusive = true } // Pag 7.8.3.2
                    }
                },
                onBack = { // Pag 7.8.4
                    navController.popBackStack() // Pag 7.8.4.1
                }
            )
        }

        composable(Routes.HOME) { // Pag 7.9
            if (currentUser == null) { // Pag 7.9.1
                navController.navigate(Routes.LOGIN) { popUpTo(Routes.HOME) { inclusive = true } } // Pag 7.9.1.1
                return@composable // Pag 7.9.1.2
            }

            HomeScreen( // Pag 7.9.2
                authViewModel = authViewModel, // Pag 7.9.2.1
                bookViewModel = bookViewModel, // Pag 7.9.2.2
                onNavigateToAddBook = { // Pag 7.9.2.3
                    navController.navigate(Routes.ADDBOOK) // Pag 7.9.2.3.1
                },
                onBookClick = { bookId -> // Pag 7.9.2.4
                    navController.navigate("${Routes.EDITBOOK.replace("{bookId}", bookId.toString())}") // Pag 7.9.2.4.1
                },
                onLogout = { // Pag 7.9.2.5
                    authViewModel.logout() // Pag 7.9.2.5.1
                    navController.navigate(Routes.LOGIN) { popUpTo(Routes.HOME) { inclusive = true } } // Pag 7.9.2.5.2
                },
                onNavigateToFavoriteBooks = { // Pag 7.9.2.6
                    navController.navigate(Routes.FAVORITE_BOOKS) // Pag 7.9.2.6.1
                }
            )
        }

        composable(Routes.ADDBOOK) { // Pag 7.10
            if (currentUser?.role == "admin") { // Pag 7.10.1
                AddBookScreen( // Pag 7.10.2
                    bookViewModel = bookViewModel, // Pag 7.10.2.1
                    onBookAdded = { // Pag 7.10.2.2
                        navController.popBackStack() // Pag 7.10.2.2.1
                    },
                    onBack = { // Pag 7.10.2.3
                        navController.popBackStack() // Pag 7.10.2.3.1
                    }
                )
            } else { // Pag 7.10.3
                navController.popBackStack() // Pag 7.10.3.1
            }
        }

        composable(Routes.EDITBOOK) { backStackEntry -> // Pag 7.11
            val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull() // Pag 7.11.1

            if (bookId != null) { // Pag 7.11.2
                EditBookScreen( // Pag 7.11.3
                    bookId = bookId, // Pag 7.11.3.1
                    bookViewModel = bookViewModel, // Pag 7.11.3.2
                    onBack = { // Pag 7.11.3.3
                        navController.popBackStack() // Pag 7.11.3.3.1
                    },
                    onBookDeleted = { // Pag 7.11.3.4
                        navController.popBackStack() // Pag 7.11.3.4.1
                    },
                    isAdmin = currentUser?.role == "admin" // Pag 7.11.3.5
                )
            } else { // Pag 7.11.4
                navController.popBackStack() // Pag 7.11.4.1
            }
        }

        composable(Routes.FAVORITE_BOOKS) { // Pag 7.12
            FavoriteBooksScreen( // Pag 7.12.1
                bookViewModel = bookViewModel, // Pag 7.12.1.1
                onBookClick = { bookId -> // Pag 7.12.1.2
                    navController.navigate("${Routes.EDITBOOK.replace("{bookId}", bookId.toString())}") // Pag 7.12.1.2.1
                },
                onBack = { // Pag 7.12.1.3
                    navController.popBackStack() // Pag 7.12.1.3.1
                }
            )
        }
    }
}