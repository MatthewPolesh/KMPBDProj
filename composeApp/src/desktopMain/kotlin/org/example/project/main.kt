package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.di.repositoryModule
import org.example.project.di.viewModelModule
import org.example.project.presentation.gui.MainScreen
import org.koin.core.context.startKoin


fun main() = application {
    startKoin {
        modules(
            repositoryModule,
            viewModelModule
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
       MainScreen()
    }
}
