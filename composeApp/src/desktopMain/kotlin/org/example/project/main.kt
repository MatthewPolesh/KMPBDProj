package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.data.database.initDatabase
import org.example.project.di.databaseModule
import org.example.project.di.repositoryModule
import org.example.project.di.viewModelModule
import org.example.project.presentation.gui.AuthScreen
import org.jetbrains.exposed.sql.Database
import org.koin.core.context.startKoin


fun main() = application {

    startKoin {
        modules(
            repositoryModule,
            viewModelModule,
            databaseModule
        )
    }

    val database:Database = org.koin.core.context.GlobalContext.get().get()
    initDatabase(database)

    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        AuthScreen()
    }
}
