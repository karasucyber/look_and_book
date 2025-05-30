# 📚 LivrariaAppV2

![Capa do Aplicativo - Exemplo da Home Screen](https://via.placeholder.com/600x300?text=Home+Screen) ![Capa do Aplicativo - Exemplo da Login Screen](https://via.placeholder.com/600x300?text=Login+Screen) O LivrariaAppV2 é um aplicativo Android moderno que simula um sistema de livraria, permitindo que usuários naveguem por um catálogo de livros, gerenciem favoritos e, para administradores, realizem o gerenciamento completo do acervo. Construído com Kotlin e Jetpack Compose, o aplicativo exemplifica as melhores práticas de desenvolvimento Android com uma arquitetura MVVM, persistência de dados local com Room e um sistema de autenticação robusto.

## ✨ Funcionalidades

* **Catálogo de Livros:** Visualize uma lista de livros disponíveis, com imagem, título, autor, preço e quantidade.
* **Pesquisa:** Filtre livros por título ou autor através de uma barra de busca dinâmica.
* **Gestão de Favoritos:**
    * Usuários logados podem marcar/desmarcar livros como favoritos.
    * Visualização dedicada dos livros favoritos de cada usuário.
    * Favoritos são persistidos por usuário, mesmo após o fechamento do app.
* **Autenticação e Autorização:**
    * Login e Registro de usuários com hashing seguro de senhas (BCrypt).
    * Diferenciação de papéis: `user` (usuário comum) e `admin` (administrador).
* **Funcionalidades Administrativas:** (Apenas para usuários com perfil `admin`)
    * **Adicionar Livro:** Cadastro de novos livros no catálogo.
    * **Editar Livro:** Modificação de detalhes de livros existentes.
    * **Ativar/Desativar Livro:** Controle de visibilidade de livros no catálogo para usuários comuns (livros desativados não aparecem para `user`).
* **Persistência de Dados Local:** Usuários, livros e favoritos são armazenados localmente no dispositivo.

## 🚀 Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias e bibliotecas:

* **Kotlin**: Linguagem de programação primária.
* **Jetpack Compose**: Kit de ferramentas moderno para construir interfaces de usuário nativas no Android de forma declarativa.
* **Android Architecture Components**:
    * **ViewModel**: Gerencia e expõe dados relacionados à UI de forma otimizada para o ciclo de vida.
    * **Room Persistence Library**: Camada de abstração do SQLite para persistência de dados local, com suporte a `Flow` para reatividade.
    * **Navigation Compose**: Gerencia a navegação entre as telas do aplicativo.
    * **DataStore Preferences**: Armazenamento persistente e assíncrono de pares chave-valor (utilizado para favoritos).
* **Kotlin Coroutines & Flow**: Para programação assíncrona reativa e gerenciamento de streams de dados.
* **BCrypt (JBCrypt)**: Biblioteca para hashing seguro de senhas, crucial para a segurança das credenciais de usuário.
* **Coil**: Biblioteca leve e rápida para carregamento e exibição de imagens de rede.
* **Material Design 3**: Componentes de UI que seguem as diretrizes mais recentes do Material Design.

## 📐 Arquitetura

O projeto segue rigorosamente a arquitetura **MVVM (Model-View-ViewModel)**, promovendo uma separação clara de responsabilidades:

* **Model (Camada de Dados)**: Encapsula a lógica de negócio e de acesso a dados. Inclui as entidades de dados (`Book`, `User`), Data Access Objects (DAOs) para interagir com o Room, o banco de dados `AppDatabase`, o `FavoriteManager` (para persistência de favoritos) e os repositórios (`AuthRepository`, `BookRepository`).
* **View (Camada de UI)**: Responsável pela renderização da interface do usuário. Consiste em `@Composable` functions que observam o estado exposto pelos ViewModels e reagem a eventos do usuário. Ex: `LoginScreen`, `HomeScreen`, `BookCard`.
* **ViewModel (Camada de Lógica da UI)**: Atua como um intermediário entre a View e o Model. Contém a lógica de apresentação e o estado da UI, exposto através de `StateFlow`s. Os ViewModels se comunicam com os repositórios para buscar e manipular dados, isolando a View da complexidade da camada de dados. Ex: `AuthViewModel`, `BookViewModel`.

## 📂 Estrutura do Projeto

A estrutura do projeto é organizada em pacotes lógicos para facilitar a manutenção e escalabilidade:

├── LivrariaAppV2
├── data
│   ├── local                  // Gerenciamento de preferências locais (FavoriteManager)
│   │   └── FavoriteManager.kt
│   ├── model                  // Definição das classes de dados (Book, User)
│   │   ├── Book.kt
│   │   └── User.kt
│   ├── AppDatabase.kt         // Configuração do banco de dados Room
│   ├── BookDao.kt             // Interface DAO para operações de livros
│   └── UserDao.kt             // Interface DAO para operações de usuários
├── navigation
│   └── AppNavHost.kt          // Configuração do grafo de navegação
├── repository
│   ├── AuthRepository.kt      // Repositório para autenticação de usuários
│   └── BookRepository.kt      // Repositório para operações de livros
├── ui
│   ├── theme                  // Definições de tema e cores
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   ├── AddBookScreen.kt       // Tela para adicionar novos livros
│   ├── BookCard.kt            // Componente reutilizável para exibir um livro
│   ├── CatalogScreen.kt       // Tela de listagem do catálogo de livros
│   ├── EditBookScreen.kt      // Tela para visualizar/editar detalhes do livro
│   ├── FavoriteBooksScreen.kt // Tela para exibir livros favoritos
│   ├── HomeScreen.kt          // Tela principal após o login
│   ├── LoginScreen.kt         // Tela de login do usuário
│   ├── RegisterScreen.kt      // Tela de registro de novo usuário
│   └── SearchBar.kt           // Componente de barra de busca
├── viewmodel
│   ├── AuthViewModel.kt       // ViewModel para lógica de autenticação
│   ├── AuthViewModelFactory.kt// Factory para AuthViewModel
│   ├── BookViewModel.kt       // ViewModel para lógica de livros e favoritos
│   └── BookViewModelFactory.kt// Factory para BookViewModel
├── LivrariaAppV2Application.kt// Classe Application para configurações globais
└── MainActivity.kt            // Atividade principal do aplicativo


## ⚙️ Como Configurar e Rodar o Projeto

1.  **Pré-requisitos**:
    * Android Studio Flamingo (ou versão mais recente)
    * JDK 11 ou superior
    * Um dispositivo Android (físico ou emulador) com API 24 (Android 7.0) ou superior.

2.  **Clonar o Repositório**:
    ```bash
    git clone [https://github.com/seu-usuario/LivrariaAppV2.git](https://github.com/seu-usuario/LivrariaAppV2.git)
    cd LivrariaAppV2
    ```

3.  **Abrir no Android Studio**:
    * Abra o projeto no Android Studio.
    * O Gradle deve sincronizar automaticamente as dependências. Caso contrário, clique em `File` > `Sync Project with Gradle Files`.

4.  **Rodar o Aplicativo**:
    * Conecte um dispositivo Android ou inicie um emulador.
    * Clique no botão `Run` (ícone de play verde) na barra de ferramentas do Android Studio.

## 🔑 Credenciais de Teste

O aplicativo vem com alguns dados de teste pré-definidos no banco de dados para facilitar a demonstração.

* **Usuário Administrador:**
    * **Usuário:** `admin`
    * **Senha:** `admin123`
* **Usuário Comum:**
    * **Usuário:** `user`
    * **Senha:** `user123`

Você também pode registrar novos usuários e administradores (após o login de um admin existente, a funcionalidade de adicionar/editar papéis não está na UI, mas pode ser adicionada via console do Room para testes, ou você pode modificar o código de registro para permitir escolher o papel).

## 🖼️ Capturas de Tela (Screenshots)

## 🛠️ Próximos Passos e Possíveis Melhorias

* **Persistência de Sessão**: Manter o usuário logado mesmo após o fechamento do aplicativo (salvando credenciais no DataStore).
* **Validação de Entrada da UI**: Implementar validações mais robustas e feedback visual instantâneo para campos de formulário.
* **Tratamento de Erros Aprimorado**: Utilizar `Snackbar` para mensagens de erro/sucesso temporárias e telas de erro dedicadas.
* **Recuperação de Senha**: Implementar um fluxo "Esqueci a Senha" (requereria um backend).
* **Internacionalização**: Adicionar suporte a múltiplos idiomas.
* **Testes**: Desenvolver testes unitários, de integração e de UI para garantir a estabilidade e qualidade do código.
* **UX/UI**: Refinar a interface para uma experiência de usuário ainda mais fluida e intuitiva.
* **Migrações de Banco de Dados**: Implementar migrações de Room para preservar dados do usuário em futuras atualizações do esquema do banco de dados.

---
