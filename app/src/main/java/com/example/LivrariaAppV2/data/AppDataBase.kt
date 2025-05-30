package com.example.LivrariaAppV2.data // Pag 6

import android.content.Context // Pag 6.1
import androidx.room.Database // Pag 6.1
import androidx.room.Room // Pag 6.1
import androidx.room.RoomDatabase // Pag 6.1
import androidx.sqlite.db.SupportSQLiteDatabase // Pag 6.1
import com.example.LivrariaAppV2.data.dao.BookDao // Pag 6.1
import com.example.LivrariaAppV2.data.dao.UserDao // Pag 6.1
import com.example.LivrariaAppV2.data.model.Book // Pag 6.1
import com.example.LivrariaAppV2.data.model.User // Pag 6.1
import kotlinx.coroutines.CoroutineScope // Pag 6.1
import kotlinx.coroutines.Dispatchers // Pag 6.1
import kotlinx.coroutines.SupervisorJob // Pag 6.1
import kotlinx.coroutines.launch // Pag 6.1
import org.mindrot.jbcrypt.BCrypt // Pag 6.1

@Database(entities = [Book::class, User::class], version = 1, exportSchema = false) // Pag 6.2
abstract class AppDatabase : RoomDatabase() { // Pag 6.2

    abstract fun bookDao(): BookDao // Pag 6.3
    abstract fun userDao(): UserDao // Pag 6.4

    companion object { // Pag 6.5
        @Volatile // Pag 6.5.1
        private var INSTANCE: AppDatabase? = null // Pag 6.5.1

        fun getDatabase(context: Context): AppDatabase { // Pag 6.5.2
            return INSTANCE ?: synchronized(this) { // Pag 6.5.2
                val instance = Room.databaseBuilder( // Pag 6.5.3
                    context.applicationContext, // Pag 6.5.3
                    AppDatabase::class.java, // Pag 6.5.3
                    "livraria_database" // Pag 6.5.3
                )
                    .fallbackToDestructiveMigration() // Pag 6.5.4
                    .addCallback(AppDatabaseCallback(context)) // Pag 6.5.5
                    .build() // Pag 6.5.3
                INSTANCE = instance // Pag 6.5.6
                instance // Pag 6.5.6
            }
        }
    }

    private class AppDatabaseCallback( // Pag 6.6
        private val context: Context // Pag 6.6
    ) : RoomDatabase.Callback() { // Pag 6.6
        override fun onCreate(db: SupportSQLiteDatabase) { // Pag 6.6.1
            super.onCreate(db) // Pag 6.6.1
            INSTANCE?.let { database -> // Pag 6.6.2
                val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO) // Pag 6.6.3
                scope.launch { // Pag 6.6.4
                    populateDatabase(database.bookDao(), database.userDao()) // Pag 6.6.5
                }
            }
        }

        // Função para preencher o banco de dados // Pag 6.7
        suspend fun populateDatabase(bookDao: BookDao, userDao: UserDao) { // Pag 6.7
            // Adicionar usuário admin padrão se não existir nenhum usuário // Pag 6.7.1
            val adminUsername = "admin" // Pag 6.7.1
            val adminPassword = "admin123" // Senha padrão para o admin // Pag 6.7.1
            val adminPasswordHash = BCrypt.hashpw(adminPassword, BCrypt.gensalt()) // Hash da senha do admin // Pag 6.7.1

            val defaultAdmin = User(username = adminUsername, passwordHash = adminPasswordHash, role = "admin") // Pag 6.7.2

            if (userDao.getUserByUsername(defaultAdmin.username) == null) { // Pag 6.7.3
                userDao.insertUser(defaultAdmin) // Pag 6.7.3
            }

            // Adicionar usuário regular padrão // Pag 6.7.4
            val regularUsername = "user" // Pag 6.7.4
            val regularPassword = "user123" // Senha padrão para o usuário // Pag 6.7.4
            val regularPasswordHash = BCrypt.hashpw(regularPassword, BCrypt.gensalt()) // Pag 6.7.4

            val defaultUser = User(username = regularUsername, passwordHash = regularPasswordHash, role = "user") // Pag 6.7.5
            if (userDao.getUserByUsername(defaultUser.username) == null) { // Pag 6.7.6
                userDao.insertUser(defaultUser) // Pag 6.7.6
            }


            val books = listOf( // Pag 6.8
                Book( // Pag 6.8.1
                    id = 1,
                    title = "Dom Quixote",
                    author = "Miguel de Cervantes",
                    description = "Um fidalgo que enlouquece ao ler muitos romances de cavalaria e decide se tornar um cavaleiro andante.",
                    price = 45.90,
                    quantity = 10,
                    imageUrl = "https://blogdabn.wordpress.com/wp-content/uploads/2017/04/cerv.jpg",
                    isActive = true,
                    genre = "Clássico"
                ),
                Book( // Pag 6.8.2
                    id = 2,
                    title = "1984",
                    author = "George Orwell",
                    description = "Uma distopia clássica sobre vigilância governamental e manipulação da verdade.",
                    price = 32.50,
                    quantity = 15,
                    imageUrl = "https://m.media-amazon.com/images/I/81+C2pQc1xL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Distopia"
                ),
                Book( // Pag 6.8.3
                    id = 3,
                    title = "O Pequeno Príncipe",
                    author = "Antoine de Saint-Exupéry",
                    description = "Uma fábula poética com ilustrações em aquarela, que narra a história de um piloto que cai no deserto e encontra um pequeno príncipe de outro planeta.",
                    price = 28.00,
                    quantity = 20,
                    imageUrl = "https://m.media-amazon.com/images/I/71KkL9B8zML._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Literatura Infantil"
                ),
                Book( // Pag 6.8.4
                    id = 4,
                    title = "Cem Anos de Solidão",
                    author = "Gabriel García Márquez",
                    description = "A história épica da família Buendía através de várias gerações na fictícia cidade de Macondo.",
                    price = 55.00,
                    quantity = 8,
                    imageUrl = "https://m.media-amazon.com/images/I/71+v9q+iYEL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Realismo Mágico"
                ),
                Book( // Pag 6.8.5
                    id = 5,
                    title = "Orgulho e Preconceito",
                    author = "Jane Austen",
                    description = "Um romance clássico sobre a sociedade inglesa do século XIX, amor, casamento e classes sociais.",
                    price = 30.00,
                    quantity = 12,
                    imageUrl = "https://m.media-amazon.com/images/I/71Q1tPupgdL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Romance"
                ),
                Book( // Pag 6.8.6
                    id = 6,
                    title = "Crime e Castigo",
                    author = "Fiódor Dostoiévski",
                    description = "Um estudante pobre planeja e executa o assassinato de uma velha agiota e sua irmã.",
                    price = 48.00,
                    quantity = 7,
                    imageUrl = "https://m.media-amazon.com/images/I/51w9S9rBqPL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Clássico"
                ),
                Book( // Pag 6.8.7
                    id = 7,
                    title = "O Senhor dos Anéis",
                    author = "J.R.R. Tolkien",
                    description = "Uma trilogia de alta fantasia que narra a busca para destruir o Um Anel e derrotar o Senhor do Escuro Sauron.",
                    price = 70.00,
                    quantity = 5,
                    imageUrl = "https://m.media-amazon.com/images/I/71V2xQ8N+WL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Fantasia"
                ),
                Book( // Pag 6.8.8
                    id = 8,
                    title = "A Culpa é das Estrelas",
                    author = "John Green",
                    description = "Um romance sobre dois adolescentes com câncer que se apaixonam em um grupo de apoio.",
                    price = 29.90,
                    quantity = 18,
                    imageUrl = "https://m.media-amazon.com/images/I/81+Xl969ZcL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Romance"
                ),
                Book( // Pag 6.8.9
                    id = 9,
                    title = "O Alquimista",
                    author = "Paulo Coelho",
                    description = "Um jovem pastor de ovelhas da Andaluzia viaja para o deserto egípcio em busca de um tesouro.",
                    price = 22.00,
                    quantity = 25,
                    imageUrl = "https://m.media-amazon.com/images/I/61KxG8J6uCL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Aventura"
                ),
                Book( // Pag 6.8.10
                    id = 10,
                    title = "Sapiens: Uma Breve História da Humanidade",
                    author = "Yuval Noah Harari",
                    description = "Uma visão abrangente da história da humanidade desde a Idade da Pedra até o século XXI.",
                    price = 60.00,
                    quantity = 9,
                    imageUrl = "https://m.media-amazon.com/images/I/71+yR+e1qHL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "História"
                ),
                Book( // Pag 6.8.11
                    id = 11,
                    title = "Extraordinário",
                    author = "R.J. Palacio",
                    description = "A história de Auggie Pullman, um menino com deformidade facial, ao iniciar o quinto ano em uma escola regular.",
                    price = 33.00,
                    quantity = 14,
                    imageUrl = "https://m.media-amazon.com/images/I/91t8r4Q12vL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Drama"
                ),
                Book( // Pag 6.8.12
                    id = 12,
                    title = "A Menina que Roubava Livros",
                    author = "Markus Zusak",
                    description = "Durante a Segunda Guerra Mundial, uma jovem chamada Liesel Meminger rouba livros e compartilha com outros enquanto a morte a observa.",
                    price = 38.00,
                    quantity = 11,
                    imageUrl = "https://m.media-amazon.com/images/I/51wJ-E1t8FL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "História"
                ),
                Book( // Pag 6.8.13
                    id = 13,
                    title = "Harry Potter e a Pedra Filosofal",
                    author = "J.K. Rowling",
                    description = "A primeira aventura do jovem bruxo Harry Potter em Hogwarts.",
                    price = 40.00,
                    quantity = 22,
                    imageUrl = "https://m.media-amazon.com/images/I/71+D2Y45LBL._AC_UF1000,1000_QL80_.jpg",
                    isActive = true,
                    genre = "Fantasia"
                )
            )
            books.forEach { bookDao.insertBook(it) } // Pag 6.9
        }
    }
}