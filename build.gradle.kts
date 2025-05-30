// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false // <<< CORRIGIDO: Agora usa 'kotlin.android' para combinar com libs.versions.toml
    // REMOVIDO: 'alias(libs.plugins.kotlin.compose) apply false' não é necessário aqui.
}