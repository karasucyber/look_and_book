package com.example.LivrariaAppV2.data.local // Pag 3

import android.content.Context // Pag 3.1
import androidx.datastore.core.DataStore // Pag 3.1
import androidx.datastore.preferences.core.Preferences // Pag 3.1
import androidx.datastore.preferences.core.edit // Pag 3.1
import androidx.datastore.preferences.core.stringSetPreferencesKey // Pag 3.1
import androidx.datastore.preferences.preferencesDataStore // Pag 3.1
import kotlinx.coroutines.flow.Flow // Pag 3.1
import kotlinx.coroutines.flow.map // Pag 3.1

// Extensão para obter o DataStore de preferências // Pag 3.2
// Definido fora da classe para ser um singleton para o aplicativo // Pag 3.2
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_favorites") // Pag 3.2

class FavoriteManager(private val context: Context) { // Pag 3.3

    private val dataStore = context.dataStore // Pag 3.3.1

    // Prefixo para gerar chaves de favoritos por usuário // Pag 3.3.2
    private val USER_FAVORITE_IDS_PREFIX = "favorite_book_ids_for_user_" // Pag 3.3.2

    // Função para gerar a chave específica para o usuário // Pag 3.3.3
    private fun getFavoriteKeyForUser(userId: String): Preferences.Key<Set<String>> { // Pag 3.3.3
        return stringSetPreferencesKey(USER_FAVORITE_IDS_PREFIX + userId) // Pag 3.3.3
    }

    /** // Pag 3.4
     * Retorna um Flow do conjunto de IDs de livros favoritos para um usuário específico. // Pag 3.4
     * @param userId O ID do usuário para o qual buscar os favoritos. // Pag 3.4
     */ // Pag 3.4
    fun getFavoriteBookIds(userId: String): Flow<Set<Int>> { // Pag 3.4
        return dataStore.data // Pag 3.4.1
            .map { preferences -> // Pag 3.4.2
                preferences[getFavoriteKeyForUser(userId)] // Pag 3.4.2
                    ?.map { it.toInt() } // Pag 3.4.3
                    ?.toSet() // Pag 3.4.4
                    ?: emptySet() // Pag 3.4.5
            }
    }

    /** // Pag 3.5
     * Adiciona um livro à lista de favoritos de um usuário. // Pag 3.5
     * @param userId O ID do usuário. // Pag 3.5
     * @param bookId O ID do livro a ser adicionado. // Pag 3.5
     */ // Pag 3.5
    suspend fun addFavoriteBook(userId: String, bookId: Int) { // Pag 3.5
        dataStore.edit { preferences -> // Pag 3.5.1
            val key = getFavoriteKeyForUser(userId) // Pag 3.5.2
            val currentIds = preferences[key]?.toMutableSet() ?: mutableSetOf() // Pag 3.5.2
            currentIds.add(bookId.toString()) // Pag 3.5.3
            preferences[key] = currentIds // Pag 3.5.4
        }
    }

    /** // Pag 3.6
     * Remove um livro da lista de favoritos de um usuário. // Pag 3.6
     * @param userId O ID do usuário. // Pag 3.6
     * @param bookId O ID do livro a ser removido. // Pag 3.6
     */ // Pag 3.6
    suspend fun removeFavoriteBook(userId: String, bookId: Int) { // Pag 3.6
        dataStore.edit { preferences -> // Pag 3.6.1
            val key = getFavoriteKeyForUser(userId) // Pag 3.6.2
            val currentIds = preferences[key]?.toMutableSet() ?: mutableSetOf() // Pag 3.6.2
            currentIds.remove(bookId.toString()) // Pag 3.6.3
            preferences[key] = currentIds // Pag 3.6.4
        }
    }

    /** // Pag 3.7
     * Limpa todos os favoritos de um usuário específico. // Pag 3.7
     * @param userId O ID do usuário. // Pag 3.7
     */ // Pag 3.7
    suspend fun clearAllFavoritesForUser(userId: String) { // Pag 3.7
        dataStore.edit { preferences -> // Pag 3.7.1
            preferences.remove(getFavoriteKeyForUser(userId)) // Pag 3.7.2
        }
    }
}