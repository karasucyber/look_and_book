package com.example.LivrariaAppV2 // Pag 24

import android.app.Application // Pag 24.1
import coil.Coil // Pag 24.1
import coil.ImageLoader // Pag 24.1
import coil.util.DebugLogger // Pag 24.1

class LivrariaAppV2Application : Application() { // Pag 24.2
    override fun onCreate() { // Pag 24.3
        super.onCreate() // Pag 24.3.1
        val imageLoader = ImageLoader.Builder(this) // Pag 24.3.2
            .logger(DebugLogger()) // Pag 24.3.2.1
            .build() // Pag 24.3.2.2
        Coil.setImageLoader(imageLoader) // Pag 24.3.3
    }
}